package com.example.service;

import com.example.entity.dto.Book;

public interface BookUploadService {
    public boolean updateBookcover(int bookId, String bookCoverUrl, String bookCoverUuid);
    public Book findBookById(int bookId);
}
