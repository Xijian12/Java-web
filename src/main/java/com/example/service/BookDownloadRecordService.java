package com.example.service;

import com.example.entity.vo.request.user.BookRequest;
import com.example.entity.vo.request.user.MaterialPage;

public interface BookDownloadRecordService {
    void addBookDownloadRecord(BookRequest book);
    MaterialPage queryBookDownloadRecord(String userEmail, Integer page, Integer pageSize);
    void deleteBookDownloadRecord(Integer downloadId);
}
