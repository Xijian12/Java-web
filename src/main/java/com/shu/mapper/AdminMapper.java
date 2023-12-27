package com.shu.mapper;

import com.shu.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 85131
 */
@Mapper
public interface AdminMapper {
    void add(@Param("account")String account,@Param("password") String password);//添加管理员

    Admin findByAccount(@Param("account")String account);//查找管理员

    void deleteAdminByAccount(@Param("account")String account);//删除管理员

    void deleteUserByEmail(@Param("email")String email);//删除用户

    void updateAdminInformation(@Param("account")String account,@Param("password")String password);//更新管理员信息

    void updateUserInformation(@Param("id")Integer id,@Param("account") String account, @Param("password") String password,
                               @Param("nickname") String nickname, @Param("points") Integer points,
                               @Param("email") String email);//更新用户信息

}
