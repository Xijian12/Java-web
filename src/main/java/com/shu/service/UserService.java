package com.shu.service;

import com.shu.pojo.User;

public interface UserService {
    //根据邮箱查询用户
    User findByEmail(String email);

    //注册
    void register(String userAccount, String userPassword, String nickName, Integer points, String email);
}
