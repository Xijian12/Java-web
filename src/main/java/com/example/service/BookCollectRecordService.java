package com.example.service;

import com.example.entity.vo.request.user.BookCollectRecord;
import com.example.entity.vo.request.user.BookRequest;
import com.example.entity.vo.request.user.MaterialPage;

public interface BookCollectRecordService {
    void addBookCollectRecord(BookRequest bookRequest);
    MaterialPage queryBookCollectRecord(String userEmail, Integer page, Integer pageSize);
    void deleteBookCollectRecord(Integer collectId);
    String addBookCollectRecord(BookCollectRecord bookCollectRecord);
}
