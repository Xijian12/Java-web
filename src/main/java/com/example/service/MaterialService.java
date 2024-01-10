package com.example.service;

import com.example.entity.vo.request.DeleteMaterialRequest;
import com.example.entity.vo.request.Material;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MaterialService {
    void newAddMaterial(Material material);

    Material GetMaterialData(Integer materId);

    //更新资料信息
    void updateMaterial(Material material);

    boolean deleteMaterialByUser(DeleteMaterialRequest deleteMaterialRequest) throws Exception;

    boolean deleteMaterialById(Integer materialId) throws Exception;
}
