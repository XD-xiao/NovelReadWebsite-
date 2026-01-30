import request from "@/utils/request.js";

export const getUserInfoService = ( token ) =>
    request.post('/api/user/getUserInfo' , {token})

export const getUserViewHistoryService = ( token ) =>
    request.post('/api/user/getUserViewHistory' , {token})

export const getUserFavoriteService = ( token ) =>
    request.post('/api/user/getUserFavorite' , {token})

export const getUserRatingService = ( token ) =>
    request.post('/api/user/getUserRating' , {token})

export const addFavoriteService = ( token , bookId ) =>
    request.post('/api/user/addFavoriteService' , {
        token, bookId
    })

export const deleteFavoriteService = ( token , bookId ) =>
    request.post('/api/user/deleteFavoriteService', {
        token, bookId
    })

export const addRatingService = ( token , bookId ,rating) =>
    request.post('/api/user/addRatingService', {
        token, bookId , rating
    })




