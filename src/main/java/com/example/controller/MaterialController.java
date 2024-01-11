package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Result;
import com.example.entity.vo.request.DeleteMaterialRequest;
import com.example.entity.vo.request.DonwloadMaterialVO;
import com.example.entity.vo.request.Material;
import com.example.entity.vo.request.admin.AdminAddCommentVO;
import com.example.entity.vo.request.admin.AdminDeleteCommentVO;
import com.example.entity.vo.request.user.UserAddCommentVO;
import com.example.entity.vo.request.user.UserDeleteCommentVO;
import com.example.entity.vo.response.MaterialUploadVO;
import com.example.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    //新增资料信息
    @PostMapping
    public Result newAddMaterial(@RequestBody  Material material) {
        materialService.newAddMaterial(material);

        return Result.success();
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
        boolean FALG = materialService.deleteMaterialByType(deleteMaterialRequest);

        if(FALG == false){
            return Result.error("删除失败，无法删除别人上传的资料");
        }
        return Result.success();
    }
    //管理员删除资料信息
    @DeleteMapping("admin")
    public Result deleteMaterialByAdmin(@RequestBody DeleteMaterialRequest deleteMaterialRequest) throws Exception {
        boolean FALG = materialService.deleteMaterialByType(deleteMaterialRequest);

        if(FALG == false){
            return Result.error("删除失败，管理员账号无效");
        }
        return Result.success();
    }

    //下载图书，用户的管理员均可以下载
    @PostMapping("/download")
    public Result downloadMaterial(@RequestBody DonwloadMaterialVO donwloadMaterialVO) throws IOException {
        log.info("materialId：{}",donwloadMaterialVO.getMaterialId());
        log.info("userEmail：{}",donwloadMaterialVO.getUserEmail());
        log.info("type：{}",donwloadMaterialVO.getType());
        String downloadUrl = materialService.downloadMaterial(donwloadMaterialVO);

        if(downloadUrl == null){
            return Result.error("用户积分不足");
        }
        return Result.success(downloadUrl);
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
    public Result getMaterialsByUserEmail(@RequestParam String userEmail) {
        List<Material> materials = materialService.getMaterialsByUserEmail(userEmail);
        MaterialUploadVO response = new MaterialUploadVO();
        response.setSize(materials.size());
        response.setMaterials(materials);
        return Result.success(response);
    }

}
