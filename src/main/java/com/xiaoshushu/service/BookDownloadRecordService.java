package com.xiaoshushu.service;

import com.xiaoshushu.entity.vo.request.user.BookRequest;
import com.xiaoshushu.entity.vo.request.user.MaterialPage;

public interface BookDownloadRecordService {
    void addBookDownloadRecord(BookRequest book);
    MaterialPage queryBookDownloadRecord(String userEmail, Integer page, Integer pageSize);
    void deleteBookDownloadRecord(Integer downloadId);
}
