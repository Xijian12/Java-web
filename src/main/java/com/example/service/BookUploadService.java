package com.example.service;

import com.example.pojo.Book;

public interface BookUploadService {
    public boolean updateBookcover(int bookId, String bookCoverUrl, String bookCoverUuid);
    public Book findBookById(int bookId);
    public boolean updateBookFile(int bookId, String bookFileUUID);
}
