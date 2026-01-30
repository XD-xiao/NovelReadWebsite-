<script setup>
import { User, Lock, Message, Ticket } from '@element-plus/icons-vue'
import { ref, watch } from 'vue'
import { useUserStore } from '@/stores'
import { useRouter } from 'vue-router'
import {
  getVerificationCodeService,
  userLoginService,
  userRegisterService
} from '@/api/user.js'

const isRegister = ref(false)

const formModel = ref({
  email: '',
  username: '',
  gender: '',
  verificationCode: ''
})

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' },
    { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
  ],
  gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
  verificationCode: [
    { required: true, message: '请输入验证码', trigger: 'blur' },
    { pattern: /^[0-9]{3,8}$/, message: '验证码为3-8位数字', trigger: 'blur' }
  ]
}

const form = ref()
const router = useRouter()
const userStore = useUserStore()

const register = async () => {
  await form.value.validate()
  await userRegisterService(formModel.value)
  isRegister.value = false
}

const login = async () => {
  await form.value.validate()
  const res = await userLoginService(formModel.value)
  userStore.setToken(res.data.data)
  await router.push('/')
}

const timer = ref('获取验证码')
const isCounting = ref(false)

const getCode = async () => {
  if (isCounting.value || !formModel.value.email) {
    // 可选：先校验邮箱是否填写
    return
  }

  await getVerificationCodeService(formModel.value.email)

  let countDown = 50
  isCounting.value = true
  timer.value = `${countDown}s`

  const interval = setInterval(() => {
    countDown--
    if (countDown > 0) {
      timer.value = `${countDown}s`
    } else {
      clearInterval(interval)
      timer.value = '获取验证码'
      isCounting.value = false
    }
  }, 1000)
}

// 切换时重置表单
watch(isRegister, () => {
  formModel.value = {
    email: '',
    username: '',
    gender: '',
    verificationCode: ''
  }
  if (form.value) form.value.resetFields()
})
</script>

<template>
  <div class="login-page">
    <el-form
        :model="formModel"
        :rules="rules"
        ref="form"
        size="large"
        autocomplete="off"
        class="form-container"
    >
      <!-- 标题 -->
      <el-form-item>
        <h1>{{ isRegister ? '注册' : '登录' }}</h1>
      </el-form-item>

      <!-- 邮箱 -->
      <el-form-item prop="email">
        <el-input
            v-model="formModel.email"
            :prefix-icon="Message"
            placeholder="请输入邮箱"
        ></el-input>
      </el-form-item>

      <!-- 用户名（仅注册） -->
      <el-form-item v-if="isRegister" prop="username">
        <el-input
            v-model="formModel.username"
            :prefix-icon="User"
            placeholder="请输入用户名"
        ></el-input>
      </el-form-item>

      <!-- 性别（仅注册） -->
      <el-form-item v-if="isRegister" prop="gender">
        <el-select
            v-model="formModel.gender"
            placeholder="请选择性别"
            style="width: 100%"
        >
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
          <el-option label="其他" value="other"></el-option>
        </el-select>
      </el-form-item>

      <!-- 验证码输入 + 按钮（共用） -->
      <el-form-item prop="verificationCode">
        <div class="code-input-group">
          <el-input
              v-model="formModel.verificationCode"
              :prefix-icon="Ticket"
              placeholder="请输入验证码"
              class="code-input"
          ></el-input>
          <el-button
              class="code-button"
              type="info"
              round
              :disabled="isCounting || !formModel.email"
              @click="getCode"
          >
            {{ timer }}
          </el-button>
        </div>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item>
        <el-button
            @click="isRegister ? register() : login()"
            class="submit-button"
            type="primary"
            auto-insert-space
            style="width: 100%"
        >
          {{ isRegister ? '注册' : '登录' }}
        </el-button>
      </el-form-item>

      <!-- 切换链接 -->
      <el-form-item class="switch-link">
        <el-link
            type="info"
            :underline="false"
            @click="isRegister = !isRegister"
        >
          {{ isRegister ? '← 返回登录' : '注册 →' }}
        </el-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f7fa;
}

.form-container {
  width: 100%;
  max-width: 400px;
  padding: 30px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);

  h1 {
    text-align: center;
    margin-bottom: 24px;
    font-weight: 600;
    color: #333;
  }

  .code-input-group {
    display: flex;
    gap: 12px;

    .code-input {
      flex: 1;
    }

    .code-button {
      width: auto;
      min-width: 110px;
    }
  }

  .switch-link {
    text-align: center;
    margin-top: 16px;
  }
}
</style>
