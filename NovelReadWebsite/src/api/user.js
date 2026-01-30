import request from "@/utils/request.js";


export const userRegisterService = ({email,username,gender,verificationCode}) =>
    request.post('/api/user/register', {
        email,
        username,
        gender,
        verificationCode,
    })

export const getVerificationCodeService = (email) =>
    request.post('/api/user/getVerificationCode', {
        email,
    })

export const userLoginService = ({email,verificationCode}) =>
    request.post('/api/user/login', {
        email,
        verificationCode,
    })


