package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.vo.request.Material;
import com.example.entity.vo.request.user.MaterialPage;
import com.example.entity.vo.request.user.SchoolMajorSubject;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialMapper {

    void insertMaterial(Material material);

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

    @Delete("delete from  materials where material_id = #{mateialId}")
    void delelteMaterialById(int mateialId);

    void updateMaterial(Material material);

    //学校点击量查询 学校下载量查询
    // 学校下载量查询
    @Select("SELECT sum(material_download_num) AS downloadNum, sum(material_click_num) AS clickNum FROM materials where school= #{school}")
    Map<String, Integer> countDownloadClickNumBySchool(String school);
    // 类别点击量查询
    @Select("SELECT sum(material_download_num) AS downloadNum, sum(material_click_num) AS clickNum FROM materials where major= #{major}")
    Map<String, Integer> countDownloadClickNumByCategory(String major);



    // 查询最高的前n类资料
    @Select("SELECT * FROM materials ORDER BY (material_grade * #{weight1} + material_click_num * #{weight2} + material_download_num * #{weight3}) DESC LIMIT #{n}")
    List<Material> selectTopNMaterials(@Param("weight1") double weight1, @Param("weight2") double weight2, @Param("weight3") double weight3, @Param("n") int n);

    @Select("SELECT material_download_num, material_click_num FROM materials WHERE material_id = #{materialId}")
    Map<String, Integer> getDownloadClicksByMaterialId(int materialId);

    @Select("SELECT * FROM materials WHERE material_uploader = #{userEmail}")
    List<Material> selectMaterialsByUserEmail(String userEmail);


    List<Material> selectMaterialByCondition(String school, String major, String subject,
                                           Integer materialGradeFloor, Integer materialGradeUpper);

    @Update("UPDATE materials SET material_uploader = #{adminEmail} WHERE material_uploader = #{email}")
    void updateUploaderByEmail(@Param("email") String email, @Param("adminEmail") String adminEmail);


    @Delete("DELETE FROM commentformaterial WHERE user_email = #{email}")
    void deleteByUserEmail(@Param("email") String email);

    @Select("select distinct(school) from materials")
    List<String> selectAllSchool();

    @Select("select distinct(major) from materials where school = #{school}")
    List<String> selectMajorBySchool(SchoolMajorSubject schoolMajorSubject);

    @Select("select distinct(subject) from materials where school = #{school} and major = #{major}")
    List<String> selectSubjectBySchoolAndMajorl(SchoolMajorSubject schoolMajorSubject);
}
