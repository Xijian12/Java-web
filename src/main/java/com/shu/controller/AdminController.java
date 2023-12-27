package com.shu.controller;

import com.shu.pojo.Admin;
import com.shu.pojo.AdminResult;
import com.shu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/register")
    public AdminResult registerAdmin (String Admin_account, String Admin_password) {
        Admin admin = adminService.findAdminByAccount(Admin_account);
        if(admin==null){
            adminService.register(Admin_account, Admin_password);//没有占用
            System.out.println("查询成功");
            return AdminResult.success();
        }
        else{
            return AdminResult.error("管理员已经存在");
        }
    }


}
