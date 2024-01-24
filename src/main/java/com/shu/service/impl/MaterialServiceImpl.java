package com.shu.service.impl;

import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.DeleteMaterialRequest;
import com.shu.entity.vo.request.DonwloadMaterialVO;
import com.shu.entity.vo.request.Material;
import com.shu.entity.vo.request.MaterialComment;
import com.shu.entity.vo.request.admin.AdminAddCommentVO;
import com.shu.entity.vo.request.admin.AdminDeleteCommentVO;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.entity.vo.request.user.UserAddCommentVO;
import com.shu.entity.vo.request.user.UserDeleteCommentVO;
import com.shu.mapper.MaterialCommentMapper;
import com.shu.mapper.MaterialMapper;
import com.shu.service.AccountService;
import com.shu.service.MaterialService;
import com.shu.utils.AliOSSUtils;
import com.shu.utils.Constants;
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
    public String newAddMaterial(Material material) {
        material.setMaterialClickNum(0);
        material.setMaterialDownloadNum(0);
        material.setCreateTime(LocalDateTime.now());
        material.setUpdateTime(LocalDateTime.now());
        if(material.getSchool() == null || material.getSchool().equals("")
            ||material.getSubject() == null || material.getSubject().equals("")
            ||material.getMajor() == null || material.getMajor().equals("")){
            return "学校、学科、专业均不能为空";
        }
        Material originMaterial = materialMapper.selectRepeatMaterial(material.getSchool(),material.getMajor(),material.getSubject(),material.getMaterialUploader());
        if(originMaterial != null){
            return "不能重复上传资料";
        }
        if((material.getElecBookUuid()==null || material.getElecBookUuid().equals(""))
            && (material.getTeachingPlanUuid()==null || material.getTeachingPlanUuid().equals(""))
            && (material.getClassPptUuid()==null || material.getClassPptUuid().equals(""))
            && (material.getCalendarVolumeUuid()==null || material.getCalendarVolumeUuid().equals(""))
            && (material.getAnotherMaterialUuid()==null || material.getAnotherMaterialUuid().equals(""))){
            return "请至少上传一种资料";
        }
        if(material.getMaterialCoverUrl()==null || material.getMaterialCoverUrl().equals("")){//如果没有地址，则采用默认封面
            material.setMaterialCoverUrl(Constants.defaultMaterialCoverUrl);
        }
        Account account = accountService.findAccountByNameOrEmail(material.getMaterialUploader());
        log.info("account:{}",account);
        if(account != null){
            String newUserInfo = accountService.updateUserPoints(account.getEmail(),account.getPoints() + Constants.uploadPointBonus);
        }
        materialMapper.insertMaterial(material);
        return null;
    }

    @Override
    public Material GetMaterialData(Integer materialId) {
        Material material = materialMapper.selectMaterialById(materialId);
        materialMapper.updateMaterialClickNum(material.getMaterialClickNum() + 1,materialId);
        return material;
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
    public String deleteMaterialByType(DeleteMaterialRequest deleteMaterialRequest) throws Exception {
        String returnError = null;
        Account ISADMIN = accountService.findAccountByNameOrEmail(deleteMaterialRequest.getAdminAccount());
        log.info("AdminAccount:{}",ISADMIN);
        List<Integer> materialIds = deleteMaterialRequest.getMaterialIds();
        String userEmail = deleteMaterialRequest.getUserEmail();
        Integer type = deleteMaterialRequest.getType();
        log.info("materialId：{}",materialIds);
        log.info("userEmail：{}",userEmail);
        log.info("type：{}",type);
        if(materialIds == null) return "传入的资料ID为空";
        for (int i = 0; i < materialIds.size(); i++) {
            Material material = materialMapper.selectMaterialById(materialIds.get(i));
            //删除对应的资料和资料在云存储的地址
            if (material != null) {
                if (material.getMaterialUploader().equals(userEmail) || ISADMIN != null) {
                    switch (type) {
                        case 0: {
                            if (material.getMaterialCoverUuid() != null &&!material.getMaterialCoverUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getMaterialCoverUuid());
                            if (material.getElecBookUuid() != null &&!material.getElecBookUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getElecBookUuid());
                            if (material.getTeachingPlanUuid() != null && !material.getTeachingPlanUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getTeachingPlanUuid());
                            if (material.getClassPptUuid() != null && !material.getClassPptUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getClassPptUuid());
                            if (material.getCalendarVolumeUuid() != null && !material.getCalendarVolumeUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getCalendarVolumeUuid());
                            if (material.getAnotherMaterialUuid() != null && !material.getAnotherMaterialUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getAnotherMaterialUuid());
                            //如果这本资料的封面不是默认封面，这删除该封面
                            if(!material.getMaterialCoverUrl().equals(Constants.defaultMaterialCoverUrl) &&!material.getMaterialCoverUrl().equals(""))
                                aliOSSUtils.DeleteFile(material.getMaterialCoverUuid());
                            materialMapper.delelteMaterialById(material.getMaterialId());
                            break;
                        }
                        case 1: {
                            if (material.getElecBookUuid() != null &&!material.getElecBookUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getElecBookUuid());
                            material.setElecBookPoints(0);
                            material.setElecBookUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 2: {
                            if (material.getTeachingPlanUuid() != null && !material.getTeachingPlanUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getTeachingPlanUuid());
                            material.setTeachingPlanPoints(0);
                            material.setTeachingPlanUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 3: {
                            if (material.getClassPptUuid() != null && !material.getClassPptUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getClassPptUuid());
                            material.setClassPptPoints(0);
                            material.setClassPptUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 4: {
                            if (material.getCalendarVolumeUuid() != null&& !material.getCalendarVolumeUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getCalendarVolumeUuid());
                            material.setCalendarVolumePoints(0);
                            material.setCalendarVolumeUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                        case 5: {
                            if (material.getAnotherMaterialUuid() != null && !material.getAnotherMaterialUuid().equals(""))
                                aliOSSUtils.DeleteFile(material.getAnotherMaterialUuid());
                            material.setAnotherMaterialPoints(0);
                            material.setAnotherMaterialUuid(null);
                            material.setUpdateTime(LocalDateTime.now());
                            materialMapper.updateMaterial(material);
                            break;
                        }
                    }
                }
                else returnError = "不能删除别人上传的资料，且你不是管理员";
                materialCommentMapper.deleteMaterialCommentBySMST(material.getSchool()
                ,material.getMajor(),material.getSubject(),type);
            }
            else returnError = "有一本资料不存在";
        }
        return null;
    }

    //下载某个类型的图书资料
    @Override
    public String downloadMaterial(DonwloadMaterialVO donwloadMaterialVO) throws IOException {
        Account account = accountService.findAccountByNameOrEmail(donwloadMaterialVO.getUserEmail());
        if(account == null) return "用户不存在";
        Material material = materialMapper.selectMaterialById(donwloadMaterialVO.getMaterialId());
        if(donwloadMaterialVO.getType() > 5 || donwloadMaterialVO.getType() < 1) return "下载类型出错";
        if(material == null) return "资料ID不正确";
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
        if(account.getPoints() > points || account.getRole().equals("admin") || account.getEmail().equals(material.getMaterialUploader())){
            if(!account.getRole().equals("admin") && !account.getEmail().equals(material.getMaterialUploader())){
                account.setPoints(account.getPoints() - points);
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
                //如果下载成功，则给上传者积分奖励
                Account uploaderAccount = accountService.findAccountByNameOrEmail(material.getMaterialUploader());
                if(uploaderAccount != null && !account.getEmail().equals(material.getMaterialUploader())){
                    String newUserInfo = accountService.updateUserPoints(uploaderAccount.getEmail(),uploaderAccount.getPoints() + (int)(Constants.pointRate * points));
                }
                donwloadMaterialVO.setDownloadUrl(aliOSSUtils.GetFileDownloadUrl(fileUuid,newFileName));
                materialMapper.updateMaterialDownloadNum(material.getMaterialDownloadNum() + 1,donwloadMaterialVO.getMaterialId());
                return null;
            }
            return "下载的资料不存在";
        }
        else return "用户积分不足";
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
        //只能吧查询语句放上
        Material material = materialMapper.selectMaterialById(materialId);
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<MaterialComment> materialComments = materialCommentMapper.getMaterialCommentBySMS(material.getSchool(),material.getMajor(),material.getSubject());
        for (int i=0;i<materialComments.size();i++){
            Account account = accountService.findAccountByNameOrEmail(materialComments.get(i).getUserEmail());
            if(account != null){
                materialComments.get(i).setUserAvatarUrl(account.getAvatarUrl());
            }
        }
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
    public List<String> getMajorBySchool(String school){
        return materialMapper.selectMajorBySchool(school);
    }

    @Override
    public List<String> getSubjectBySchoolAndMajor(String school,String major){
        return materialMapper.selectSubjectBySchoolAndMajorl(school,major);
    }

    //返回资料下载总量
    @Override
    public Long getMaterialTotalDownloadNum(){
        return materialMapper.selectMaterialTotalDownloadNum();
    }

    //返回资料点击总量
    @Override
    public Long getMaterialTotalClickNum(){
        return materialMapper.selectMaterialTotalClickNum();
    }

    public void addMaterialClickNum(Integer newMaterialClickNum,Integer materialId){
        materialMapper.updateMaterialClickNum(newMaterialClickNum,materialId);
    }

    //给对应资料增加下载量
    @Override
    public void addMaterialDownloadNum(Integer newMaterialDownloadNum, Integer materialId){
        materialMapper.updateMaterialDownloadNum(newMaterialDownloadNum,materialId);
    }

    //修该资料评论表的用户名
    @Override
    public void updateUsername(String oldUserName, String newUserName){
        materialMapper.updateUsername(oldUserName,newUserName);
    }

    //根据学校、专业、学科三者模糊查询
    @Override
    public MaterialPage queryMaterialBySchMajorSub(String schMajorSub, Integer page, Integer pageSize){
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<Material> materialList = materialMapper.selectMaterialBySMS(schMajorSub);
        log.info("materialList:{}",materialList);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<Material> p = (Page<Material>) materialList;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    @Override
    public MaterialPage queryMaterialByUserEmail(String userEmail, Integer page, Integer pageSize){
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<Material> materialList = materialMapper.selectMaterialsByUserEmail(userEmail);
        log.info("materialList:{}",materialList);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<Material> p = (Page<Material>) materialList;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }
}
