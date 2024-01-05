package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.vo.response.DisplayAccountByUserVO;
import com.example.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;


@Validated
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理员相关操作", description = "包括用户管理员相关信息修改等操作。")
public class UserOperatorController {
    @Resource
    AccountService accountService;
    /**
     * 用户查看个人信息
     *
     * @return 用户信息
     */
    @GetMapping("/userInfo")
    @Operation(summary = "查看用户信息")
    public RestBean<String> getUserInfo(@RequestParam String username){
        DisplayAccountByUserVO userVo=accountService.userInfo(username);
        System.out.println(userVo);
        return RestBean.success(userVo.toString());
    }

    /**
     * 针对于返回值为String作为错误信息的方法进行统一处理
     * @param action 具体操作
     * @return 响应结果
     * @param <T> 响应结果类型
     */
    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}
