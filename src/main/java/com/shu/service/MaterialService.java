package com.shu.service;

import com.shu.entity.vo.request.DeleteMaterialRequest;
import com.shu.entity.vo.request.DonwloadMaterialVO;
import com.shu.entity.vo.request.Material;
import com.shu.entity.vo.request.admin.AdminAddCommentVO;
import com.shu.entity.vo.request.admin.AdminDeleteCommentVO;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.entity.vo.request.user.UserAddCommentVO;
import com.shu.entity.vo.request.user.UserDeleteCommentVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface MaterialService {
    String newAddMaterial(Material material);

    Material GetMaterialData(Integer materId);

    //更新资料信息
    void updateMaterial(Material material);

    //通过类型和ID俩删除资料
    String deleteMaterialByType(DeleteMaterialRequest deleteMaterialRequest) throws Exception;

    //通过资料ID来删除资料
    boolean deleteMaterialById(Integer materialId) throws Exception;

    //下载资料返回文件的URL地址
    String downloadMaterial(DonwloadMaterialVO donwloadMaterialVO) throws IOException;

    //用户给资料添加评论
    boolean addMaterialCommentByUser(UserAddCommentVO userAddCommentVO);

    //管理员给资料添加评论
    public boolean addMaterialCommentByAdmin(AdminAddCommentVO adminAddCommentVO);

    //用户删除资料评论
    boolean deleteMaterialCommentByUser(UserDeleteCommentVO userDeleteCommentVO);

    //管理员删除资料评论
    boolean deleteMaterialCommentByAdmin(AdminDeleteCommentVO adminDeleteCommentVO);


    //根据专业名称查询下载量和点击量
    Map<String, Integer>  categoryClicksDownload(String professional);
    //根据学校名称查询下载量和点击量
    Map<String, Integer>  schoolClicksDownload(String school);

    //定义资料排名权重
    List<Material> getTopNMaterials(int n);

    //根据ID查询资料的下载量和点击量
    Map<String, Integer> getDownloadClicksByMaterialId(int materialId);

    //根据上传者查询详细信息
    List<Material> getMaterialsByUserEmail(String userEmail);

    MaterialPage queryMaterialByCondition(String school, String major, String subject, Integer materialGradeFloor, Integer materialGradeUpper, Integer page, Integer pageSize);

    MaterialPage queryMaterialCommentById(Integer materialId, Integer page, Integer pageSize);

    List<String> getAllSchool();

    List<String> getMajorBySchool(String school);

    List<String> getSubjectBySchoolAndMajor(String school,String major);

    Long getMaterialTotalDownloadNum();
    Long getMaterialTotalClickNum();

    void addMaterialClickNum(Integer newMaterialClickNum,Integer materialId);

    //给对应资料增加下载量
    void addMaterialDownloadNum(Integer newMaterialDownloadNum, Integer materialId);

    //修该资料评论表的用户名
    void updateUsername(String oldUserName, String newUserName);

    //根据学校、专业、学科三者模糊查询
    MaterialPage queryMaterialBySchMajorSub(String schMajorSub, Integer page, Integer pageSize);

    MaterialPage queryMaterialByUserEmail(String userEmail, Integer page, Integer pageSize);
}
