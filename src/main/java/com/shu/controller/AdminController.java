package com.shu.controller;

import com.shu.pojo.AdminResult;
import com.shu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 85131
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/add")
    public AdminResult registerAdmin (String adminAccount, String adminPassword) {
//        Admin admin = adminService.findAdminByAccount(adminAccount);
          String admin = adminService.findByAccount(adminAccount);
        if(admin==null){
//            System.out.println("现在没用户");
            adminService.add(adminAccount, adminPassword);//没有占用
            System.out.println("查询成功");
            return AdminResult.success();
        }
        else{
            return AdminResult.error("管理员已经存在");
        }
    }
    

}
