package com.example.service;

import com.example.entity.vo.request.Book;
import com.example.entity.vo.request.Material;

public interface MaterialUploadService {
    public boolean updateMaterialcover(int materialId, String materialCoverUrl, String materialCoverUuid);
    public Material findMaterialById(int materialId);
    public boolean updateMaterialFile(int bookId, String materialFileUUID,int type);
}
