package com.example.service.impl;

import com.example.entity.vo.request.DeleteMaterialRequest;
import com.example.entity.vo.request.Material;
import com.example.mapper.MaterialMapper;
import com.example.service.MaterialService;
import com.example.utils.AliOSSUtils;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Override
    public void newAddMaterial(Material material) {
        material.setMaterialClickNum(0);
        material.setMaterialDownloadNum(0);
        material.setCreateTime(LocalDateTime.now());
        material.setUpdateTime(LocalDateTime.now());
        materialMapper.insertMaterial(material);
    }

    @Override
    public Material GetMaterialData(Integer materId) {
        return materialMapper.selectMaterialById(materId);
    }

    @Override
    public void updateMaterial(Material material) {
        material.setUpdateTime(LocalDateTime.now());
        materialMapper.updateMaterial(material);
    }

    @Override
    public boolean deleteMaterialById(Integer materialId) throws Exception {
        Material material = materialMapper.selectMaterialById(materialId);
        if(material == null){
            return false;
        }else{
            if (material.getMaterialCoverUuid() != null)
                aliOSSUtils.DeleteFile(material.getMaterialCoverUuid());
            if (material.getElecBookUuid() != null) aliOSSUtils.DeleteFile(material.getElecBookUuid());
            if (material.getTeachingPlanUuid() != null)
                aliOSSUtils.DeleteFile(material.getTeachingPlanUuid());
            if (material.getClassPptUuid() != null) aliOSSUtils.DeleteFile(material.getClassPptUuid());
            if (material.getCalendarVolumeUuid() != null)
                aliOSSUtils.DeleteFile(material.getCalendarVolumeUuid());
            if (material.getAnotherMaterialUuid() != null)
                aliOSSUtils.DeleteFile(material.getAnotherMaterialUuid());
            materialMapper.delelteMaterialById(material.getMaterialId());
        }
        return true;
    }

    @Override
    public boolean deleteMaterialByUser(DeleteMaterialRequest deleteMaterialRequest) throws Exception {
        boolean FALG = true;
        List<Integer> materialId = deleteMaterialRequest.getMaterialIds();
        String userEmail = deleteMaterialRequest.getUserEmail();
        Integer type = deleteMaterialRequest.getType();
        if(materialId == null) return false;
        for (int i = 0; i < materialId.size(); i++) {
            Material material = materialMapper.selectMaterialById(materialId.get(i));
            if (material != null) {
                if (material.getMaterialUploader().equals(userEmail)) {
                    switch (type) {
                        case 0: {
                            if (material.getMaterialCoverUuid() != null)
                                aliOSSUtils.DeleteFile(material.getMaterialCoverUuid());
                            if (material.getElecBookUuid() != null) aliOSSUtils.DeleteFile(material.getElecBookUuid());
                            if (material.getTeachingPlanUuid() != null)
                                aliOSSUtils.DeleteFile(material.getTeachingPlanUuid());
                            if (material.getClassPptUuid() != null) aliOSSUtils.DeleteFile(material.getClassPptUuid());
                            if (material.getCalendarVolumeUuid() != null)
                                aliOSSUtils.DeleteFile(material.getCalendarVolumeUuid());
                            if (material.getAnotherMaterialUuid() != null)
                                aliOSSUtils.DeleteFile(material.getAnotherMaterialUuid());
                            materialMapper.delelteMaterialById(material.getMaterialId());
                            break;
                        }
                        case 1: {
                            if (material.getElecBookUuid() != null)
                                aliOSSUtils.DeleteFile(material.getElecBookUuid());
                            material.setElecBookPoints(0);
                            material.setElecBookUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 2: {
                            if (material.getTeachingPlanUuid() != null)
                                aliOSSUtils.DeleteFile(material.getTeachingPlanUuid());
                            material.setTeachingPlanPoints(0);
                            material.setTeachingPlanUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 3: {
                            if (material.getClassPptUuid() != null)
                                aliOSSUtils.DeleteFile(material.getClassPptUuid());
                            material.setClassPptPoints(0);
                            material.setClassPptUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 4: {
                            if (material.getCalendarVolumeUuid() != null)
                                aliOSSUtils.DeleteFile(material.getCalendarVolumeUuid());
                            material.setCalendarVolumePoints(0);
                            material.setCalendarVolumeUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 5: {
                            if (material.getAnotherMaterialUuid() != null)
                                aliOSSUtils.DeleteFile(material.getAnotherMaterialUuid());
                            material.setAnotherMaterialPoints(0);
                            material.setAnotherMaterialUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                    }
                } else FALG = false;
            }
        }
        return FALG;
    }
}
