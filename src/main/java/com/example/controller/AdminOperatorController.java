package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.vo.response.DisplayAccountByAdminVO;
import com.example.entity.vo.response.DisplayAccountByUserVO;
import com.example.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;



@Validated
@RestController
@RequestMapping("/admin")
@Tag(name = "用户管理员相关操作", description = "包括用户管理员相关信息修改等操作。")
public class AdminOperatorController {
    @Resource
    AccountService accountService;
    /**
     * 管理员查看用户个人信息
     *
     * @return 用户信息
     */
    @GetMapping("/userInfo")
    @Operation(summary = "查看用户信息")
    public RestBean<DisplayAccountByUserVO> getUserInfo(@RequestParam String username){
        return this.messageHandle(() ->
                accountService.userInfo(username));
    }
    /**
     * 管理员查看用户个人信息
     *
     * @return 用户信息
     */
    @GetMapping("/adminInfo")
    @Operation(summary = "查看管理员信息")
    public RestBean<DisplayAccountByAdminVO> getAdminInfo(@RequestParam String username){
        return this.messageHandle(() ->
                accountService.adminInfo(username));
    }
    /**
     * 针对于返回值为String作为错误信息的方法进行统一处理
     * @param action 具体操作
     * @return 响应结果
     * @param <T> 响应结果类型
     */
    private <T> RestBean<T> messageHandle(Supplier<T> action){
        T result= action.get();
        if(result == null)
            return RestBean.success(result);
        else
            return RestBean.failure(400, "未找到相关信息");
    }
}
