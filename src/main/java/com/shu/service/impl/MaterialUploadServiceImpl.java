package com.shu.service.impl;

import com.shu.entity.vo.request.Material;
import com.shu.mapper.MaterialMapper;
import com.shu.service.MaterialUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MaterialUploadServiceImpl implements MaterialUploadService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public boolean updateMaterialcover(int materialId, String materialCoverUrl, String materialCoverUuid) {
        Material material = findMaterialById(materialId);
        if (material != null) {
            materialMapper.updateMaterialcover(materialId,materialCoverUrl, materialCoverUuid);
            return true;
        }
        return false;
    }

    @Override
    public Material findMaterialById(int materialId) {
        return materialMapper.selectMaterialById(materialId);
    }

    /*
    type参数可以取值为{0,1,2,3,4,5}，
    type=0代表全部资料，
    type=1代表elec_book，
    type=2代表teaching_plan，
    type=3代表class_ppt，
    type=4代表calendar_volume，
    type=5代表another_material。（
    其中elec_book、teaching_plan、class_ppt、calendar_volume、
    another_material均是该课程下的资料类型，前端可以提供一个下拉框给用户选择）
     */
    @Override
    public boolean updateMaterialFile(int materialId, String materialFileUUID, int type) {
        Material material = findMaterialById(materialId);
        if (material != null) {
            switch (type){
                case 1: {
                    materialMapper.updatElecBook(materialId,materialFileUUID);
                    break;
                }
                case 2:{
                    materialMapper.updateTeachingPlanBook(materialId,materialFileUUID);
                    break;
                }
                case 3:{
                    materialMapper.updateClassPPT(materialId,materialFileUUID);
                    break;
                }
                case 4:{
                    materialMapper.updateCalendarVolume(materialId,materialFileUUID);
                    break;
                }
                case 5:{
                    materialMapper.updateAnotherMaterial(materialId,materialFileUUID);
                    break;
                }
            }
            return true;
        }
        return false;
    }




}
