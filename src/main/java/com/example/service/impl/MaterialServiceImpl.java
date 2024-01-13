package com.example.service.impl;

import com.example.entity.dto.Account;
import com.example.entity.vo.request.DeleteMaterialRequest;
import com.example.entity.vo.request.DonwloadMaterialVO;
import com.example.entity.vo.request.Material;
import com.example.entity.vo.request.MaterialComment;
import com.example.entity.vo.request.admin.AdminAddCommentVO;
import com.example.entity.vo.request.admin.AdminDeleteCommentVO;
import com.example.entity.vo.request.user.MaterialPage;
import com.example.entity.vo.request.user.SchoolMajorSubject;
import com.example.entity.vo.request.user.UserAddCommentVO;
import com.example.entity.vo.request.user.UserDeleteCommentVO;
import com.example.mapper.MaterialCommentMapper;
import com.example.mapper.MaterialMapper;
import com.example.service.AccountService;
import com.example.service.MaterialService;
import com.example.utils.AliOSSUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Resource
    private AccountService accountService;
    @Autowired
    private MaterialCommentMapper materialCommentMapper;

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

    //通过ID来删除资料
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

    //通过类型和用户或管理员账号来删除资料
    @Override
    public boolean deleteMaterialByType(DeleteMaterialRequest deleteMaterialRequest) throws Exception {
        boolean FALG = true;
        Account ISADMIN = accountService.findAccountByNameOrEmail(deleteMaterialRequest.getAdminAccount());
        log.info("AdminAccount:{}",ISADMIN);
        List<Integer> materialIds = deleteMaterialRequest.getMaterialIds();
        String userEmail = deleteMaterialRequest.getUserEmail();
        Integer type = deleteMaterialRequest.getType();
        log.info("materialId：{}",materialIds);
        log.info("userEmail：{}",userEmail);
        log.info("type：{}",type);
        if(materialIds == null) return false;
        for (int i = 0; i < materialIds.size(); i++) {
            Material material = materialMapper.selectMaterialById(materialIds.get(i));
            //删除对应的资料和资料在云存储的地址
            if (material != null) {
                if (material.getMaterialUploader().equals(userEmail) || ISADMIN != null) {
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
                }
                else FALG = false;
                materialCommentMapper.deleteMaterialCommentBySMST(material.getSchool()
                ,material.getMajor(),material.getSubject(),type);
            }
            else FALG = false;
        }
        return FALG;
    }

    //下载某个类型的图书资料
    @Override
    public String downloadMaterial(DonwloadMaterialVO donwloadMaterialVO) throws IOException {
        Account account = accountService.findAccountByNameOrEmail(donwloadMaterialVO.getUserEmail());
        if(account == null) return null;
        Material material = materialMapper.selectMaterialById(donwloadMaterialVO.getMaterialId());
        Integer points = null;
        String fileUuid = null;
        String materialname = null;
        switch (donwloadMaterialVO.getType()){
            case 1:{
                points = material.getElecBookPoints();
                fileUuid = material.getElecBookUuid();
                materialname = "电子书";
                break;
            }
            case 2:{
                points = material.getTeachingPlanPoints();
                fileUuid = material.getTeachingPlanUuid();
                materialname = "教学计划";
                break;
            }
            case 3:{
                points = material.getClassPptPoints();
                fileUuid = material.getClassPptUuid();
                materialname = "课程PPT";
                break;
            }
            case 4:{
                points = material.getCalendarVolumePoints();
                fileUuid = material.getCalendarVolumeUuid();
                materialname = "历年卷";
                break;
            }
            case 5:{
                points = material.getAnotherMaterialPoints();
                fileUuid = material.getAnotherMaterialUuid();
                materialname = "其他资料";
                break;
            }
        }
        if(account.getPoints() > points || account.getRole().equals("admin")){
            if(!account.getRole().equals("admin")){
                account.setPoints(account.getPoints() - material.getElecBookPoints());
                accountService.updateUserInfo(account.getUsername(),account.getUsername(),account.getPassword(),account.getPoints());
            }
            if(fileUuid != null){
                int lastDotIndex = fileUuid.lastIndexOf(".");
                // 使用 substring 提取扩展名部分
                String fileExtension = null;
                if (lastDotIndex != -1 && lastDotIndex < fileUuid.length() - 1) {
                    fileExtension = fileUuid.substring(lastDotIndex + 1);
                }
                String newFileName = material.getSchool()+"_"+material.getMajor()+"_"+material.getSubject()+"_"+materialname+ '.' + fileExtension;
                //String newFileName = material.
                return aliOSSUtils.GetFileDownloadUrl(fileUuid,newFileName);
            }
            return null;
        }
        else return null;
    }

    //用户给资料添加评论
    @Override
    public boolean addMaterialCommentByUser(UserAddCommentVO userAddCommentVO){
        Account account = accountService.findAccountByNameOrEmail(userAddCommentVO.getUserEmail());
        if(account == null){
            return false;
        }else{
            Material material = materialMapper.selectMaterialById(userAddCommentVO.getMaterialId());
            if(material != null){
                MaterialComment materialComment = new MaterialComment();
                materialComment.setSchool(material.getSchool());
                materialComment.setMajor(material.getMajor());
                materialComment.setSubject(material.getSubject());
                materialComment.setUserNickname(userAddCommentVO.getUserNickname());
                materialComment.setUserEmail(userAddCommentVO.getUserEmail());
                materialComment.setMaterialComment(userAddCommentVO.getUserComment());
                materialComment.setMaterialGrade(userAddCommentVO.getUserGrade());
                materialComment.setType(userAddCommentVO.getType());
                materialComment.setUserId(account.getId());
                materialComment.setRole("user");
                materialComment.setCreateTime(LocalDateTime.now());
                materialComment.setUpdateTime(LocalDateTime.now());
                materialCommentMapper.insertMaterialComment(materialComment);
                return true;
            }
            return false;
        }
    }

    //管理员给资料添加评论
    @Override
    public boolean addMaterialCommentByAdmin(AdminAddCommentVO adminAddCommentVO){
        Account account = accountService.findAccountByNameOrEmail(adminAddCommentVO.getAdminAccount());
        if(account == null){
            return false;
        }else{
            Material material = materialMapper.selectMaterialById(adminAddCommentVO.getMaterialId());
            if(material != null){
                MaterialComment materialComment = new MaterialComment();
                materialComment.setSchool(material.getSchool());
                materialComment.setMajor(material.getMajor());
                materialComment.setSubject(material.getSubject());
                materialComment.setUserNickname("管理员");
                materialComment.setUserEmail(adminAddCommentVO.getAdminAccount());
                materialComment.setMaterialComment(adminAddCommentVO.getAdminComment());
                materialComment.setMaterialGrade(adminAddCommentVO.getAdminGrade());
                materialComment.setType(adminAddCommentVO.getType());
                materialComment.setUserId(account.getId());
                materialComment.setRole("admin");
                materialComment.setCreateTime(LocalDateTime.now());
                materialComment.setUpdateTime(LocalDateTime.now());
                materialCommentMapper.insertMaterialComment(materialComment);
                return true;
            }
            return false;
        }
    }

    //用户删除资料评论
    @Override
    public boolean deleteMaterialCommentByUser(UserDeleteCommentVO userDeleteCommentVO){
        Account account = accountService.findAccountByNameOrEmail(userDeleteCommentVO.getUserEmail());
        if(account == null) {
            return false;
        }else{
            MaterialComment materialComment = materialCommentMapper.getMaterialCommentById(userDeleteCommentVO.getCommentId());
            if(materialComment != null){
                if(!materialComment.getUserEmail().equals(userDeleteCommentVO.getUserEmail())) return false;
                materialCommentMapper.deleteMaterialCommentById(userDeleteCommentVO.getCommentId());
            }

        }
        return true;
    }

    //管理员删除资料评论
    @Override
    public boolean deleteMaterialCommentByAdmin(AdminDeleteCommentVO adminDeleteCommentVO){
        Account account = accountService.findAccountByNameOrEmail(adminDeleteCommentVO.getAdminAccount());
        if(account == null  || !account.getRole().equals("admin")) {
            return false;
        }else{
            List<Integer> commentIds = adminDeleteCommentVO.getCommentIds();
            if(commentIds ==null) return false;
            for(int i=0;i<commentIds.size();i++){
                materialCommentMapper.deleteMaterialCommentById(commentIds.get(i));
            }
        }
        return true;
    }

    //根据专业名称查询下载量和点击量
    @Override
    public  Map<String, Integer> categoryClicksDownload(String major) {
        return materialMapper.countDownloadClickNumByCategory(major);
    }
    //根据学校名称查询下载量和点击量
    @Override
    public  Map<String, Integer> schoolClicksDownload(String school) {
        return materialMapper.countDownloadClickNumBySchool(school);
    }

    //定义资料排名权重
    @Override
    public List<Material> getTopNMaterials(int n) {
        double weight1 = 5;    // 资料评分的权重
        double weight2 = 1;    // 点击量的权重
        double weight3 = 2;    // 下载量的权重
        return materialMapper.selectTopNMaterials(weight1, weight2, weight3, n);
    }
    //根据ID查询资料的下载量和点击量
    @Override
    public Map<String, Integer> getDownloadClicksByMaterialId(int materialId) {
        return materialMapper.getDownloadClicksByMaterialId(materialId);
    }

    //根据上传者查询详细信息
    @Override
    public List<Material> getMaterialsByUserEmail(String userEmail) {
        return materialMapper.selectMaterialsByUserEmail(userEmail);
    }

    @Override
    public MaterialPage queryMaterialByCondition(String school, String major, String subject, Integer materialGradeFloor, Integer materialGradeUpper, Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<Material> materialList = materialMapper.selectMaterialByCondition(school,major,subject,
                materialGradeFloor,materialGradeUpper);
        log.info("materialList:{}",materialList);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<Material> p = (Page<Material>) materialList;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    @Override
    public MaterialPage queryMaterialCommentById(Integer materialId, Integer page, Integer pageSize){
        //只能吧查询语句放上面
        Material material = materialMapper.selectMaterialById(materialId);
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<MaterialComment> materialComments = materialCommentMapper.getMaterialCommentBySMS(material.getSchool(),material.getMajor(),material.getSubject());
        log.info("materialComments:{}",materialComments);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<MaterialComment> p = (Page<MaterialComment>) materialComments;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    @Override
    public List<String> getAllSchool(){
        return materialMapper.selectAllSchool();
    }

    @Override
    public List<String> getMajorBySchool(SchoolMajorSubject schoolMajorSubject){
        return materialMapper.selectMajorBySchool(schoolMajorSubject);
    }

    @Override
    public List<String> getSubjectBySchoolAndMajor(SchoolMajorSubject schoolMajorSubject){
        return materialMapper.selectSubjectBySchoolAndMajorl(schoolMajorSubject);
    }
}
