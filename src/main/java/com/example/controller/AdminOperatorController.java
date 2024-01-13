package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.Result;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.UpdateAvatarVO;
import com.example.entity.vo.request.user.MaterialPage;
import com.example.entity.vo.response.DisplayAccountByAdminVO;
import com.example.entity.vo.response.DisplayAccountByUserVO;
import com.example.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public RestBean<String> getUserInfo(@RequestParam String username){
        String userVo= accountService.userInfo(username).toString();
        return RestBean.success(userVo);
    }
    /**
     * 管理员查看用户个人信息
     *
     * @return 用户信息
     */
    @GetMapping("/adminInfo")
    @Operation(summary = "查看管理员信息")
    public RestBean<String> getAdminInfo(@RequestParam String username){
        String adminVo = accountService.adminInfo(username).toString();
        return RestBean.success(adminVo);
    }

    @PostMapping("/updateUserInfo")
    @Operation(summary = "修改用户信息")
    public RestBean<String> updateUserInfo(@RequestParam String oldUserName, @RequestParam String newUserName,
                                           @RequestParam String newPassword, @RequestParam Integer points){
        String newUserInfo=accountService.updateUserInfo(oldUserName,newUserName,newPassword,points);
        return RestBean.success("成功修改用户信息");
    }

    @PostMapping("/updateAvatar")
    @Operation(summary = "修改头像")
    public RestBean<String> updateAvatar(@RequestBody @Valid UpdateAvatarVO updateAvatarVO) {
        boolean isSuccess = accountService.updateAvatar(updateAvatarVO.getUsername(),
                updateAvatarVO.getNewAvatarUrl(),updateAvatarVO.getNewAvatarUuid());
        if (isSuccess) {
            return RestBean.success("成功修改用户头像");
        } else {
            return RestBean.failure(400,"修改头像失败");
        }
    }

    @GetMapping("/showAllAccounts")
    @Operation(summary = "显示所有用户和管理员")
    public Result showAllAccount(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        MaterialPage materialPage = accountService.getAllAccounts(page,pageSize);
        return Result.success(materialPage);
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
