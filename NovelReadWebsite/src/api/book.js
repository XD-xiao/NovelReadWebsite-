import request from "@/utils/request.js";



export const getHomePicService = () =>
    request.post('/api/home/getHomePic', {})

export const getNovelsByViewsService = (num) =>
    request.post('/api/home/getNovelsByViews', { num })

export const getNovelsByRatingService = (num) =>
    request.post('/api/home/getNovelsByRating', { num })

export const getNovelsByFavoritesService = (num) =>
    request.post('/api/home/getNovelsByFavorites', { num })

export const getSearchBooksService = (key) =>
    request.post('/api/home/searchBooks', { key  })

export const getBookInfoService = (id,token) =>
    request.post(`/api/bookInfo`,{ id, token })

export const getBookContentService = (token,chapterId) =>
    request.post(`/api/bookContent`, {token,chapterId })

export const getBookChapterService = (bookId) =>
    request.post(`/api/bookChapterList`, {bookId })
