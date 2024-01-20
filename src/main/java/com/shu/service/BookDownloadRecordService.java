package com.shu.service;

import com.shu.entity.vo.request.user.BookRequest;
import com.shu.entity.vo.request.user.MaterialPage;

public interface BookDownloadRecordService {
    void addBookDownloadRecord(BookRequest book);
    MaterialPage queryBookDownloadRecord(String userEmail, Integer page, Integer pageSize);
    void deleteBookDownloadRecord(Integer downloadId);
}
