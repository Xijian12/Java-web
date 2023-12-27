package com.shu.controller;

import com.shu.pojo.Result;
import com.shu.pojo.User;
import com.shu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String user_account, String user_password, String nickname, String email){
            //查询用户
            User u=userService.findByEmail(email);
            if(u==null){
                // 设置point默认值为1
                Integer points = 1;
                userService.register(user_account, user_password, nickname, points,email);//没有占用
                System.out.println("查询成功");
                return Result.success();
            }
            else{
                return Result.error("邮箱已经注册");
            }
    }
}



