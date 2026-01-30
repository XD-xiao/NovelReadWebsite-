import request from "@/utils/request.js";

export const getNotificationsService = (token) =>
    request.post('/api/notice/getNotifications', {
        token
    })


export const getIsNotice = (token) =>
    request.post('/api/notice/getIsNotice', {
        token
    })

