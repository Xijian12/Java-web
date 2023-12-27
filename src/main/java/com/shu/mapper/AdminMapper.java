package com.shu.mapper;

import com.shu.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

/**
 * @author 85131
 */
@Mapper
public interface AdminMapper {
    void add(@Param("adminAccount")String adminAccount,@Param("adminPassword") String adminPassword);//添加管理员

    @Results({
            @Result(column = "admin_account", property = "adminAccount"),
            @Result(column = "admin_password", property = "adminPassword")
    })
    Admin findByAccount(@Param("admin_account")String adminAccount);//查找管理员
    String findByAccountString(@Param("admin_account")String adminAccount);

    void deleteAdminByAccount(@Param("adminAccount")String adminAccount);//删除管理员

    void deleteUserByEmail(@Param("email")String email);//删除用户

    void updateAdminInformation(@Param("adminAccount")String adminAccount,@Param("adminPassword")String adminPassword);//更新管理员信息

    void updateUserInformation(@Param("id")Integer id,@Param("userAccount") String account, @Param("userPassword") String userPassword,
                               @Param("nickName") String nickName, @Param("points") Integer points,
                               @Param("email") String email);//更新用户信息

}
