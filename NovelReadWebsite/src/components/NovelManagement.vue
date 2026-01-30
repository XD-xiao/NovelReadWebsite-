<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import {
  getBookListService,
  updateBookByIdService,
  addBookService,
  deleteChapterService,
  updateChapterService,
  addChapterService,
} from "@/api/admin.js";
import {
  getBookChapterService,
  getBookContentService,
} from "@/api/book.js";

// ================= 分页相关 =================
const currentPage = ref(1);
const pageSize = ref(8);
const total = ref(0);
const tableData = ref([]); // ✅ 初始化为空数组

// 获取表格数据（仅真实请求）
const getAllTableData = async () => {
  try {
    const res = await getBookListService(currentPage.value, pageSize.value);
    tableData.value = res.data?.data?.list || [];
    total.value = res.data?.data?.total || 0;
  } catch (error) {
    ElMessage.error("获取小说列表失败");
    console.error("API Error:", error);
    tableData.value = [];
    total.value = 0;
  }
};

// 分页切换
const handlePageChange = (page: number) => {
  currentPage.value = page;
  getAllTableData();
};

onMounted(() => {
  getAllTableData();
});

// ================= 原有业务逻辑（章节管理 - 假数据）=================
const chapterList = ref([
  // { id: 1, title: "第一章：启程", content: "浩瀚星空下，少年踏上征途……" },
  // { id: 2, title: "第二章：异星遗迹", content: "在废弃星球上发现古老文明的线索。" },
  // { id: 3, title: "第三章：背叛", content: "最信任的伙伴竟然是敌方间谍。" },
  // { id: 4, title: "第四章：觉醒", content: "体内沉睡的力量终于苏醒。" },
  // { id: 5, title: "第五章：决战前夕", content: "舰队集结，大战一触即发。" },
]);

const EditOrSubmit = ref<number>(-1);
const statusOptions = ["连载中", "已完结", "下架中"];
const editCache = ref<Record<number, any>>({});

// Drawer 状态
const drawer = ref(false);
const drawerBookId = ref("");
const drawerTitle = ref("");
const currChapterId = ref<number | null>(null);
const inputTitle = ref("");
const textarea = ref("");

const handleClick = async (row) => {
  drawerBookId.value = row.id;
  console.log(row.id);
  const res = await getBookChapterService(row.id);
  chapterList.value = res.data.data;

  drawer.value = true;
  drawerTitle.value = row.title;
  if (chapterList.value.length > 0) {
    selectChapter(chapterList.value[0]);
  } else {
    startNewChapter();
  }
};
import { useUserStore } from '@/stores'
const userStore = useUserStore()
const selectChapter = async (chapter) => {
  currChapterId.value = chapter.id;
  inputTitle.value = chapter.title;

  const res = await getBookContentService(userStore.token , currChapterId.value);

  textarea.value = res.data.data.content;
};

const startNewChapter = () => {
  currChapterId.value = null;
  inputTitle.value = "";
  textarea.value = "";
};

const submitChapter = async () => {
  const title = inputTitle.value.trim();
  const content = textarea.value.trim();

  if (!title || !content) {
    ElMessage.warning("标题和内容不能为空");
    return;
  }

  if (currChapterId.value === null) {
    const newId = Math.max(0, ...chapterList.value.map(c => c.id)) + 1;
    const newChapter = {id: newId, title, content};
    chapterList.value.push(newChapter);

    const res = await addChapterService(drawerBookId.value , title, content);
    console.log(res.data.message)

  } else {
    const idx = chapterList.value.findIndex(c => c.id === currChapterId.value);
    if (idx !== -1) {
      chapterList.value[idx].title = title;
      chapterList.value[idx].content = content;
    }
    const res = await updateChapterService(currChapterId.value, title, content);
    console.log(res.data.message)
  }
};

const deleteChapter = (id: number) => {
  ElMessageBox.confirm("确定删除此章节？", "警告", {
    type: "warning"
  }).then(async () => {
    chapterList.value = chapterList.value.filter(c => c.id !== id);

    const res = await deleteChapterService(id);
    console.log(res.data.message)


    if (currChapterId.value === id) {
      if (chapterList.value.length > 0) {
        selectChapter(chapterList.value[0]);
      } else {
        startNewChapter();
      }
    }
    ElMessage.success("🗑️ 章节已删除（仅前端模拟）");
  });
};

// ================= 表格行内编辑逻辑（假数据）=================
const handleEdit = (row) => {
  editCache.value[row.id] = JSON.parse(JSON.stringify(row));
  EditOrSubmit.value = row.id;
};

const handleCancel = (row) => {
  Object.assign(row, editCache.value[row.id]);
  delete editCache.value[row.id];
  EditOrSubmit.value = -1;
};

const handleSubmit = async (row) => {
  const res = await updateBookByIdService(row.id, row.author, row.status, row.summary, row.tags, row.title);
  console.log(res.data.message)

  EditOrSubmit.value = -1;
  delete editCache.value[row.id];
};

const addTag = (row) => {
  if (!row.newTag?.trim()) return;
  if (!row.tags.includes(row.newTag.trim())) {
    row.tags.push(row.newTag.trim());
  }
  row.newTag = '';
};

const removeTag = (row, tag) => {
  const index = row.tags.indexOf(tag);
  if (index > -1) row.tags.splice(index, 1);
};

const getStatusType = (status) => {
  if (status === '连载中') return 'success';
  if (status === '已完结') return 'info';
  if (status === '下架中') return 'warning';
  return 'primary';
};

// ================= 新增小说（假数据）=================
const dialogVisible = ref(false);
const newBookForm = reactive({
  title: '',
  author: '',
  status: '连载中' as const,
  summary: '',
  tags: [] as string[],
  newTag: ''
});

const resetNewBookForm = () => {
  newBookForm.title = '';
  newBookForm.author = '';
  newBookForm.status = '连载中';
  newBookForm.summary = '';
  newBookForm.tags = [];
  newBookForm.newTag = '';
};

const addNewTagToForm = () => {
  if (!newBookForm.newTag?.trim()) return;
  if (!newBookForm.tags.includes(newBookForm.newTag.trim())) {
    newBookForm.tags.push(newBookForm.newTag.trim());
  }
  newBookForm.newTag = '';
};

const removeTagFromForm = (tag: string) => {
  const index = newBookForm.tags.indexOf(tag);
  if (index > -1) newBookForm.tags.splice(index, 1);
};

const handleAdd = () => {
  dialogVisible.value = true;
  resetNewBookForm();
};

const submitNewBook = async () => {
  const { title, author, status, summary, tags } = newBookForm;
  if (!title.trim() || !author.trim() || !summary.trim() || tags.length === 0) {
    ElMessage.warning('请填写完整信息，且至少有一个标签');
    return;
  }

  // 生成新 ID（基于当前 tableData 的最大 ID）
  const maxId = tableData.value.length > 0
      ? Math.max(...tableData.value.map(b => b.id))
      : 0;
  const newId = maxId + 1;

  const newBook = {
    id: newId,
    title: title.trim(),
    author: author.trim(),
    status,
    summary: summary.trim(),
    tags: [...tags]
  };

  const res = await addBookService(
      newBook.author,
      newBook.status,
      newBook.summary,
      newBook.tags,
      newBook.title,
  );
  if (res.data.code === 0){
    // 插入到 tableData 首部（仅前端模拟）
    tableData.value.unshift(newBook);
    dialogVisible.value = false;
    //刷新页面
    window.location.reload();
  }
  else
    console.log('❌ 新增小说失败（仅前端模拟）');


};
</script>

<template>
  <div style="padding: 20px">
    <el-button type="primary" @click="handleAdd">添加小说</el-button>

    <el-table :data="tableData" style="width: 100%; margin-top: 16px" border>
      <el-table-column fixed prop="id" label="编号" width="80" align="center" />
      <el-table-column label="封面" width="110" align="center">
        <template #default="{ row }">
          <img :src="row.cover" alt="封面" class="book-cover" />
        </template>
      </el-table-column>
      <el-table-column label="标题" width="140">
        <template #default="{ row }">
          <el-input v-if="EditOrSubmit === row.id" v-model="row.title" size="small" />
          <span v-else>{{ row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作者" width="120">
        <template #default="{ row }">
          <el-input v-if="EditOrSubmit === row.id" v-model="row.author" size="small" />
          <span v-else>{{ row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="120" align="center">
        <template #default="{ row }">
          <el-select v-if="EditOrSubmit === row.id" v-model="row.status" size="small" style="width: 100%">
            <el-option v-for="opt in statusOptions" :key="opt" :label="opt" :value="opt" />
          </el-select>
          <el-tag v-else :type="getStatusType(row.status)" size="small" effect="plain">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="lastUpdated" label="最后更新" width="130" align="center" />
      <el-table-column label="简介" min-width="200">
        <template #default="{ row }">
          <el-input
              v-if="EditOrSubmit === row.id"
              v-model="row.summary"
              type="textarea"
              :rows="2"
              size="small"
              maxlength="200"
              show-word-limit
          />
          <el-tooltip v-else effect="dark" :content="row.summary" placement="top">
            <span class="summary-text">{{ row.summary }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="标签" width="200">
        <template #default="{ row }">
          <div v-if="EditOrSubmit === row.id" class="tag-edit-list">
            <el-tag
                v-for="(tag, idx) in row.tags"
                :key="idx"
                closable
                size="small"
                @close="removeTag(row, tag)"
            >
              {{ tag }}
            </el-tag>
            <el-input
                v-model="row.newTag"
                size="small"
                placeholder="输入新标签"
                @keyup.enter="addTag(row)"
                style="width: 100px; margin-top: 4px"
            />
            <el-button size="small" @click="addTag(row)">+</el-button>
          </div>
          <div v-else class="tag-list">
            <el-tag v-for="(tag, index) in row.tags" :key="index" size="small" class="tag-item">
              {{ tag }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220" align="center">
        <template #default="{ row }">
          <div class="operation-buttons">
            <el-button type="primary" size="small" @click="handleClick(row)">目录</el-button>
            <template v-if="EditOrSubmit === row.id">
              <el-button type="success" size="small" @click="handleSubmit(row)">提交</el-button>
              <el-button type="danger" size="small" @click="handleCancel(row)">取消</el-button>
            </template>
            <template v-else>
              <el-button type="warning" size="small" @click="handleEdit(row)">编辑</el-button>
            </template>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页器 -->
    <div class="pagination-wrapper">
      <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          layout="total, prev, pager, next, jumper"
          @current-change="handlePageChange"
      />
    </div>

    <!-- 章节管理抽屉 -->
    <el-drawer v-model="drawer" :with-header="true" size="60%">
      <template #header>
        <h2>{{ drawerTitle }} - 章节管理</h2>
      </template>
      <div class="drawer-content">
        <div class="chapter-list-panel">
          <h3>章节列表</h3>
          <ul class="chapter-list">
            <li
                v-for="item in chapterList"
                :key="item.id"
                class="chapter-item"
                :class="{ active: currChapterId === item.id }"
                @click="selectChapter(item)"
            >
              {{ item.title }}
              <el-button
                  type="danger"
                  size="default"
                  circle
                  class="delete-btn"
                  @click.stop="deleteChapter(item.id)"
              >
                ×
              </el-button>
            </li>
          </ul>
          <el-button type="primary" size="small" class="add-chapter-btn" @click="startNewChapter">
            + 新增章节
          </el-button>
        </div>
        <div class="edit-panel">
          <h3>{{ currChapterId === null ? '新建章节' : '编辑章节' }}</h3>
          <el-form label-width="80px" class="chapter-form">
            <el-form-item label="标题：">
              <el-input v-model="inputTitle" placeholder="请输入章节标题" />
            </el-form-item>
            <el-form-item label="内容：" class="content-item">
              <el-input
                  v-model="textarea"
                  type="textarea"
                  :rows="10"
                  placeholder="请输入章节正文内容..."
                  class="content-input"
              />
            </el-form-item>
            <el-form-item class="submit-item">
              <el-button type="success" @click="submitChapter">
                {{ currChapterId === null ? '新增并保存' : '更新章节' }}
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </el-drawer>

    <!-- 新增小说对话框 -->
    <el-dialog v-model="dialogVisible" title="新增小说" width="500px">
      <el-form label-width="80px" :model="newBookForm">
        <el-form-item label="标题" required>
          <el-input v-model="newBookForm.title" placeholder="请输入小说标题" />
        </el-form-item>
        <el-form-item label="作者" required>
          <el-input v-model="newBookForm.author" placeholder="请输入作者名" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="newBookForm.status" style="width: 100%">
            <el-option v-for="opt in statusOptions" :key="opt" :label="opt" :value="opt" />
          </el-select>
        </el-form-item>
        <el-form-item label="简介" required>
          <el-input
              v-model="newBookForm.summary"
              type="textarea"
              :rows="3"
              maxlength="200"
              show-word-limit
              placeholder="请输入小说简介（200字以内）"
          />
        </el-form-item>
        <el-form-item label="标签" required>
          <div class="tag-edit-list">
            <el-tag
                v-for="(tag, idx) in newBookForm.tags"
                :key="idx"
                closable
                size="small"
                @close="removeTagFromForm(tag)"
            >
              {{ tag }}
            </el-tag>
            <el-input
                v-model="newBookForm.newTag"
                size="small"
                placeholder="输入新标签"
                @keyup.enter="addNewTagToForm"
                style="width: 120px; margin-top: 4px"
            />
            <el-button size="small" @click="addNewTagToForm">+</el-button>
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitNewBook">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.book-cover {
  width: 72px;
  height: 90px;
  object-fit: cover;
  border-radius: 6px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}
.book-cover:hover {
  transform: scale(1.05);
}

.summary-text {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  line-height: 1.4;
  font-size: 13px;
  color: #666;
}

.tag-list,
.tag-edit-list {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  align-items: center;
}

.tag-item {
  background-color: #f0f2f5;
  border-color: #dcdfe6;
}

.operation-buttons {
  display: flex;
  flex-wrap: nowrap;
  gap: 6px;
  justify-content: center;
}

.drawer-content {
  display: flex;
  height: calc(100vh - 120px);
  gap: 20px;
  padding: 0 16px;
}

.chapter-list-panel {
  width: 30%;
  border-right: 1px solid var(--el-border-color-light);
  padding-right: 16px;
  overflow-y: auto;
}

.chapter-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.chapter-item {
  padding: 10px 0;
  cursor: pointer;
  border-bottom: 1px dashed var(--el-border-color-light);
  position: relative;
  transition: background-color 0.2s;
}

.chapter-item:hover {
  background-color: var(--el-fill-color-light);
}

.chapter-item.active {
  background-color: var(--el-color-primary-light-9);
}

.delete-btn {
  float: right;
  margin-top: -4px;
}

.add-chapter-btn {
  margin-top: 16px;
  width: 100%;
}

.edit-panel {
  width: 70%;
  padding-left: 16px;
  display: flex;
  flex-direction: column;
}

.chapter-form {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.content-item {
  display: flex;
  flex-direction: column;
}

.submit-item {
  margin-top: auto;
  text-align: right;
}

.pagination-wrapper {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

:deep(.el-table) {
  font-size: 13px;
}
:deep(.el-table th) {
  background-color: #f8f9fa;
  font-weight: 600;
  color: #333;
}
</style>
