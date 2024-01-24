package com.shu.controller;

import com.shu.entity.Result;
import com.shu.entity.vo.request.DeleteMaterialRequest;
import com.shu.entity.vo.request.DonwloadMaterialVO;
import com.shu.entity.vo.request.Material;
import com.shu.entity.vo.request.Response;
import com.shu.entity.vo.request.admin.AdminAddCommentVO;
import com.shu.entity.vo.request.admin.AdminDeleteCommentVO;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.entity.vo.request.user.UserAddCommentVO;
import com.shu.entity.vo.request.user.UserDeleteCommentVO;
import com.shu.entity.vo.response.MaterialUploadVO;
import com.shu.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;


    //根据条件分页查询
    @GetMapping
    public Result queryMaterialByCondition(String school,String major, String subject,
                                           Integer materialGradeFloor,Integer materialGradeUpper,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize){
        MaterialPage materialPage = materialService.queryMaterialByCondition(school,major,subject,
                materialGradeFloor,materialGradeUpper,page,pageSize);
        return Result.success(materialPage);
    }

    //根据学校、专业、学科三者模糊查询
    @GetMapping("/SMS")
    public Result queryMaterialBySchMajorSub(String schMajorSub,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize){
        MaterialPage materialPage = materialService.queryMaterialBySchMajorSub(schMajorSub,page,pageSize);
        return Result.success(materialPage);
    }

    //根据资料ID分页查询该资料的所有评论
    @GetMapping("/comment")
    public Result queryMaterialComment(Integer materialId,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer pageSize){
        MaterialPage materialPage = materialService.queryMaterialCommentById(materialId,page,pageSize);
        return Result.success(materialPage);
    }

    //新增资料信息
    @PostMapping
    public ResponseEntity<?>  newAddMaterial(@RequestBody  Material material) {
        String returnInfo =  materialService.newAddMaterial(material);

        if(returnInfo == null){
            return  ResponseEntity.ok(new Response(200, "操作成功"));
        }
        return ResponseEntity.ok(new Response(400, returnInfo, null));
    }

    //用户更新资料信息
    @PutMapping("/user")
    public Result updateMaterialByUser(@RequestBody Material material){
        System.out.println(material.getMaterialProfile());
        materialService.updateMaterial(material);

        return Result.success();
    }

    //管理员更新资料信息
    @PutMapping("/admin")
    public Result updateMaterialByAdmin(@RequestBody Material material){
        System.out.println(material.getMaterialProfile());
        materialService.updateMaterial(material);

        return Result.success();
    }

    //用户删除资料信息
    @DeleteMapping("/user")
    public Result deleteMaterialByUser(@RequestBody DeleteMaterialRequest deleteMaterialRequest) throws Exception {
        String returnData = materialService.deleteMaterialByType(deleteMaterialRequest);

        if(returnData == null){
            return Result.success();

        }
        return Result.error(returnData);
    }
    //管理员删除资料信息
    @DeleteMapping("admin")
    public Result deleteMaterialByAdmin(@RequestBody DeleteMaterialRequest deleteMaterialRequest) throws Exception {
        String returnData = materialService.deleteMaterialByType(deleteMaterialRequest);

        if(returnData == null){
            return Result.success();
        }
        return Result.error(returnData);
    }

    //下载图书，用户的管理员均可以下载
    @PostMapping("/download")
    public Result downloadMaterial(@RequestBody DonwloadMaterialVO donwloadMaterialVO) throws IOException {
        log.info("materialId：{}",donwloadMaterialVO.getMaterialId());
        log.info("userEmail：{}",donwloadMaterialVO.getUserEmail());
        log.info("type：{}",donwloadMaterialVO.getType());
        String downloadUrl = materialService.downloadMaterial(donwloadMaterialVO);

        if(downloadUrl != null){
            return Result.error(downloadUrl);
        }
        return Result.success(donwloadMaterialVO.getDownloadUrl());
    }

    //用户给资料评论
    @PostMapping("/comment/user")
    public Result addMaterialCommentByUser(@RequestBody UserAddCommentVO userAddCommentVO){
        log.info("userAddCommentVO:{}",userAddCommentVO);
        boolean ISEXITSUSER = materialService.addMaterialCommentByUser(userAddCommentVO);

        if(ISEXITSUSER){
            return Result.success();
        }
        return Result.error("不存在该用户");
    }

    //管理员给资料评论
    @PostMapping("/comment/admin")
    public Result addMaterialCommentByAdmin(@RequestBody AdminAddCommentVO adminAddCommentVO){
        log.info("userAddCommentVO:{}",adminAddCommentVO);
        boolean ISEXITSUSER = materialService.addMaterialCommentByAdmin(adminAddCommentVO);

        if(ISEXITSUSER){
            return Result.success();
        }
        return Result.error("不存在该管理员");
    }

    //用户给删除资料评论
    @DeleteMapping ("/comment/user")
    public Result delelteMaterialCommentByUser(@RequestBody UserDeleteCommentVO userDeleteCommentVO){
        log.info("userAddCommentVO:{}",userDeleteCommentVO);
        boolean ISEXITSUSER = materialService.deleteMaterialCommentByUser(userDeleteCommentVO);

        if(ISEXITSUSER){
            return Result.success();
        }
        return Result.error("无权删除别人的评论");
    }

    //管理员删除资料评论
    @DeleteMapping("/comment/admin")
    public Result delelteMaterialCommentByAdmin(@RequestBody AdminDeleteCommentVO adminDeleteCommentVO){
        log.info("userAddCommentVO:{}",adminDeleteCommentVO);
        boolean ISEXITSUSER = materialService.deleteMaterialCommentByAdmin(adminDeleteCommentVO);

        if(ISEXITSUSER){
            return Result.success();
        }
        return Result.error("不存在该管理员");
    }

    //根据ID查询资料信息
    @GetMapping("/{materialId}")
    public Result GetMaterialData(@PathVariable Integer materialId){
        Material material = materialService.GetMaterialData(materialId);

        return Result.success(material);
    }

    //根据学校名称查询下载量和点击量
    @GetMapping("/getClickDownloadBySchool")
    public Result GetClickDownloadBySchool(@RequestParam String school){
        Map<String, Integer> ClickDownloadNum = materialService.schoolClicksDownload(school);
        return Result.success(ClickDownloadNum);
    }

    //根据专业名称查询下载量和点击量
    @GetMapping("/getClickDownloadByCategory")
    public Result GetClickDownloadByCategry(@RequestParam String Category){
        Map<String, Integer> ClickDownloadNum = materialService.categoryClicksDownload(Category);
        return Result.success(ClickDownloadNum );
    }

    //查询综合评分最高的前N类资料
    @GetMapping("/highest/{n}")
    public Result getTopNMaterials(@PathVariable Integer n) {
        List<Material> materials = materialService.getTopNMaterials(n);
        return Result.success(materials);
    }
    //根据ID查询资料的下载量和点击量
    @GetMapping("/downClicks/{materialId}")
    public Result getMaterialDownloadClicks(@PathVariable int materialId) {
        Map<String, Integer> data = materialService.getDownloadClicksByMaterialId(materialId);
        return Result.success(data);
    }

    //查询某个用户上传的所有资料
    @GetMapping("/userUpload")
    public Result getMaterialsByUserEmail(@RequestParam String userEmail,
                                           @RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        MaterialPage materialPage = materialService.queryMaterialByUserEmail(userEmail,page,pageSize);
        return Result.success(materialPage);
    }


    @GetMapping("/school")
    public Result getAllSchool(){
        List<String> schools = materialService.getAllSchool();

        return Result.success(schools);
    }
    @GetMapping("/school/major")
    public Result getMajorBySchool(@RequestParam String school){
        List<String> schools = materialService.getMajorBySchool(school);

        return Result.success(schools);
    }
    @GetMapping("/school/major/subject")
    public Result getSubjectBySchoolAndMajor(@RequestParam String school,String major){
        List<String> schools = materialService.getSubjectBySchoolAndMajor(school,major);

        return Result.success(schools);
    }

    //获取所有资料的点击量总和
    @GetMapping("clickNum")
    public Result getMaterialTotalClickNum(){
        return Result.success(materialService.getMaterialTotalClickNum());
    }

    //获取所有资料的下载量总和
    @GetMapping("downloadNum")
    public Result getMaterialTotalDownloadNum(){
        return Result.success(materialService.getMaterialTotalDownloadNum());
    }

}
