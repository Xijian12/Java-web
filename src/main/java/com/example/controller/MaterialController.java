package com.example.controller;

import com.example.entity.Result;
import com.example.entity.vo.request.DeleteMaterialRequest;
import com.example.entity.vo.request.Material;
import com.example.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        boolean FALG = materialService.deleteMaterialByUser(deleteMaterialRequest);

        if(FALG == false){
            return Result.success("删除失败，无法删除别人上传的图书");
        }
        return Result.success();
    }

    //根据ID查询资料信息
    @GetMapping("/{materialId}")
    public Result GetMaterialData(@PathVariable Integer materialId){
        Material material = materialService.GetMaterialData(materialId);

        return Result.success(material);
    }
}
