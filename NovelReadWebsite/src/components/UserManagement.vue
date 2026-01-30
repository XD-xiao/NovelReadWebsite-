<script setup lang="ts">
import {onMounted, ref} from 'vue';
import {
  getUserListService,
  addNoteService,
} from "@/api/admin.js";

onMounted( () =>{
  handlePageChange();
});

// ========== 响应式状态 ==========
const userTable = ref([]);
const currentPage = ref(1);
const pageSize = ref(10); // 每页10条
const total = ref(0);
const selectedRows = ref<any[]>([]);
const showDialog = ref(false);
const noticeTitle = ref('');
const noticeContent = ref('');

// ========== 模拟 API 请求（你后续替换为真实请求） ==========
const fetchUserList = async (page, size) =>{

  const res = await getUserListService(page,  pageSize.value);
  userTable.value = res.data.data.list;
  total.value = res.data.data.total;

}

// ========== 分页切换 ==========
const handlePageChange = () => {
  fetchUserList(currentPage.value, pageSize.value);
  // 清空已选项（可选行为，根据需求决定是否保留跨页选择）
  selectedRows.value = [];
}

// ========== 表格选择变化 ==========
function handleSelectionChange(selection: any[]) {
  selectedRows.value = selection;
}

// ========== 发送通知 ==========
const handleSendNotice =async () => {
  if (selectedRows.value.length === 0) {
    alert('请至少选择一个用户');
    return;
  }

  if (!noticeTitle.value.trim()) {
    alert('请输入通知标题');
    return;
  }

  if (!noticeContent.value.trim()) {
    alert('请输入通知内容');
    return;
  }

  // ✅ 这里是你需要实现的发送通知逻辑
  // 传递的数据：
  // - selectedUsers: selectedRows.value （包含 id, name, email 等）
  // - title: noticeTitle.value
  // - content: noticeContent.value
  console.log('【准备发送通知】');
  console.log('收件人:', selectedRows.value);
  console.log('标题:', noticeTitle.value);
  console.log('内容:', noticeContent.value);

  // TODO: 调用你的通知 API
  const res = await addNoteService(selectedRows.value, noticeTitle.value , noticeContent.value);
  console.log(res.data.message);

  // 发送成功后关闭弹窗并清空
  showDialog.value = false;
  noticeTitle.value = '';
  noticeContent.value = '';
}
</script>

<template>
  <div>
    <h2>用户管理</h2>

    <el-button type="primary" :disabled="userTable.length === 0" @click="showDialog = true">
      发通知
    </el-button>

    <el-table
        :data="userTable"
        border
        style="width: 100%; margin-top: 16px"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" prop="id" width="80" align="center" />
      <el-table-column label="姓名" prop="name" width="100" align="center" />
      <el-table-column label="邮箱" prop="email" min-width="200" show-overflow-tooltip />
      <el-table-column label="性别" prop="gender" width="80" align="center" />
      <el-table-column label="创建时间" prop="createTime" min-width="100" align="center" />
    </el-table>

    <div class="pagination-wrapper" style="margin-top: 16px; text-align: right">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-size="pageSize"
          :total="total"
          layout="total, prev, pager, next, jumper"
          @current-change="handlePageChange"
      />
    </div>

    <!-- 发通知对话框 -->
    <el-dialog v-model="showDialog" title="发送通知" width="500px">
      <el-form label-position="top">
        <el-form-item label="通知标题">
          <el-input v-model="noticeTitle" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="通知内容">
          <el-input
              v-model="noticeContent"
              type="textarea"
              :rows="5"
              placeholder="请输入通知内容"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="handleSendNotice">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
h2 {
  margin-bottom: 16px;
}
.pagination-wrapper {
  margin-top: 16px;
  display: flex;
  justify-content: flex-end;
}
</style>
