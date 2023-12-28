package com.shu.service;

import com.shu.pojo.User;

/**
 * @author 85131
 */
public interface UserService {
    //根据邮箱查询用户
    User findByEmail(String email);

    //注册
    void register(String userAccount, String userPassword, String nickName, Integer points, String email);

    //修改密码
    void updatePassword(String userPassword);

    //修改头像

    //修改用户信息
     void updateInformaation( String userPassword, String nickName,String email);

}
