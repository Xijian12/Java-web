package com.shu.controller;

import com.shu.pojo.Result;
import com.shu.pojo.User;
import com.shu.service.UserService;
import com.shu.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shu.utils.JwtUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 85131
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    //注册mapping
    @PostMapping("/register")

    public Result register(@Pattern(regexp = "^\\S{11}$")String userAccount,@Pattern(regexp = "^\\S{5,16}$") String userPassword, @Pattern(regexp = "^\\S{3,15}$")String nickName, String email){
            //查询用户
            User u=userService.findByEmail(email);
            if(u==null){
                // 设置point默认值为1
                Integer points = 1;
                userService.register(userAccount, userPassword, nickName, points,email);//没有占用
                System.out.println("查询成功");
                return Result.success();
            }
            else{
                return Result.error("邮箱已经注册");
            }
    }

    //登录mapping
    @PostMapping("/login")
    public Result<String> login(String email,@Pattern(regexp = "^\\S{5,16}$")String password){
        //查询用户
        User loginUser=userService.findByEmail(email);
        if (loginUser==null){
            return Result.error("用户不存在");
        }
        //判断密码是否正确
        if(loginUser.getEmail()!=null) {
            if (Md5Util.getMD5String(password).equals(loginUser.getUserPassword())) {
                Map<String,Object>claims=new HashMap();
                claims.put("id",loginUser.getId());
                claims.put("email",loginUser.getEmail());
                String token= JwtUtil.genToken(claims);
                return Result.success(token);
            }
        }
        return Result.error("密码错误");

    }
}



