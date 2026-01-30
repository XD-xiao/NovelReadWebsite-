import {defineStore} from "pinia";
import {ref} from "vue";
// import {userGetInfoService} from "@/api/user";


// 用户模块 token setToken removeToken
export const useUserStore = defineStore('userStore', () =>{
    // token
    const token = ref('');
    const setToken = (newToken) => {
        token.value = newToken
    }
    const removeToken = () => {
        token.value = ''
    }
    // 用户信息
    const user = ref({})
    const getUser  = async () => {
        // const res = await userGetInfoService()
        // user.value = res.data.data
    }



    return {
        token,
        setToken,
        removeToken,
        user,
        getUser,
    }
},{
    persist: true
})

