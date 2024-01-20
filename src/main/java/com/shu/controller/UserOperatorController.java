package com.shu.controller;

import com.shu.entity.RestBean;
import com.shu.entity.vo.request.UserInfoUpdateRequest;
import com.shu.entity.vo.response.DisplayAccountByAdminVO;
import com.shu.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;


@Slf4j
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
    public RestBean<DisplayAccountByAdminVO> getUserInfo(@RequestParam String username){
        DisplayAccountByAdminVO userVO= accountService.adminInfo(username);
        return RestBean.success(userVO);
    }

    @PostMapping("/updateUserInfo")
    @Operation(summary = "修改用户信息")
    public RestBean<String> updateUserInfo(@RequestBody UserInfoUpdateRequest userInfoRequest){
        String newUserInfo = accountService.updateUserInfo(
                userInfoRequest.getOldUserName(),
                userInfoRequest.getNewUserName(),
                userInfoRequest.getNewPassword(),
                userInfoRequest.getPoints()
        );
        return RestBean.success("成功修改用户信息");
    }
    @GetMapping("/deleteUser")
    @Operation(summary = "删除用户")
    public RestBean<String> deleteUser(@RequestParam String email) {
        String result = accountService.deleteUserAndRelatedInfo(email);
        return result == null ? RestBean.success("用户删除成功") : RestBean.failure(400, result);
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
