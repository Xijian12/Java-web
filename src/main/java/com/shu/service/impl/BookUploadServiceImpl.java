package com.shu.service.impl;

import com.shu.mapper.BookMapper;
import com.shu.entity.vo.request.Book;
import com.shu.service.BookUploadService;
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

    @Override
    public boolean updateBookFile(int bookId, String bookFileUUID){
        Book book = findBookById(bookId);
        if (book != null) {
            bookMapper.updateBookFile(bookId,bookFileUUID);
            return true;
        }
        return false;
    }
}
