import request from "@/utils/request.js";

export const getBookListService = (pageNum, pageSize) =>
    request.post('/api/bookList', { pageNum, pageSize })

export const updateBookByIdService = (id, author, status,summary,tags,title) =>
    request.post('/api/updateBookById', { id, author, status,summary,tags,title})

export const addBookService = (author, status,summary,tags,title) =>
    request.post('/api/addBook', { author, status,summary,tags,title})

export const deleteChapterService = ( chapterId) =>
    request.post('/api/deleteChapter', { chapterId })

export const updateChapterService = ( chapterId, title, content) =>
    request.post('/api/updateChapter', { chapterId, title, content})

export const addChapterService = ( bookId, title, content) =>
    request.post('/api/addChapter', { bookId, title, content})

export const getUserListService = ( pageNum, pageSize) =>
    request.post('/api/getUserList', { pageNum, pageSize })

export const addNoteService = ( userArray, title, content) =>
    request.post('/api/addNotice', { userArray, title, content })
