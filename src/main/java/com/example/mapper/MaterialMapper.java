package com.example.mapper;

import com.example.entity.vo.request.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MaterialMapper {

    //根据ID来筛选一个Material
    @Select("select * from materials where material_id = #{materialId}")
    Material selectMaterialById(int materialId);

    //更新资料封面
    @Update("update materials set material_cover_url = #{materialCoverUrl},material_cover_uuid = #{materialCoverUuid} where material_id = #{materialId}")
    void updateMaterialcover(int materialId, String materialCoverUrl, String materialCoverUuid);

    //更新电子书的UUID
    @Update("update materials set elec_book_uuid = #{materialFileUUID} where material_id = #{materialId}")
    void updatElecBook(int materialId, String materialFileUUID);

    //更新教学计划的UUID
    @Update("update materials set teaching_plan_uuid = #{materialFileUUID} where material_id = #{materialId}")
    void updateTeachingPlanBook(int materialId, String materialFileUUID);

    //更新课程PPT的UUID
    @Update("update materials set class_ppt_uuid = #{materialFileUUID} where material_id = #{materialId}")
    void updateClassPPT(int materialId, String materialFileUUID);

    //更新历年卷的UUID
    @Update("update materials set calendar_volume_uuid = #{materialFileUUID} where material_id = #{materialId}")
    void updateCalendarVolume(int materialId, String materialFileUUID);

    //更新其他资料的UUID
    @Update("update materials set another_material_uuid = #{materialFileUUID} where material_id = #{materialId}")
    void updateAnotherMaterial(int materialId, String materialFileUUID);
}
