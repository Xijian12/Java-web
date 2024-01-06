package com.example.service.impl;

import com.example.entity.dto.Book;
import com.example.mapper.AccountMapper;
import com.example.mapper.BookMapper;
import com.example.service.BookUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookUploadServiceImpl implements BookUploadService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book findBookById(int bookId) {
        return bookMapper.selectBookById(bookId);
    }

    @Override
    public boolean updateBookcover(int bookId, String bookCoverUrl, String bookCoverUuid) {
        Book book = findBookById(bookId);
        if (book != null) {
            bookMapper.updateBookcover(bookId,bookCoverUrl, bookCoverUuid);
            return true;
        }
        return false;
    }
}
