package com.xiaoshushu.service;

import com.xiaoshushu.entity.vo.request.Book;

public interface BookUploadService {
    public boolean updateBookcover(int bookId, String bookCoverUrl, String bookCoverUuid);
    public Book findBookById(int bookId);
    public boolean updateBookFile(int bookId, String bookFileUUID);
}
