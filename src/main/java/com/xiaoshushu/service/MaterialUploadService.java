package com.xiaoshushu.service;

import com.xiaoshushu.entity.vo.request.Material;

public interface MaterialUploadService {
    public boolean updateMaterialcover(int materialId, String materialCoverUrl, String materialCoverUuid);
    public Material findMaterialById(int materialId);
    public boolean updateMaterialFile(int bookId, String materialFileUUID,int type);






}
