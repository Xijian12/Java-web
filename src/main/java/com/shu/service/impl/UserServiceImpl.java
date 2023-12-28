package com.shu.service.impl;

import com.shu.mapper.UserMapper;
import com.shu.pojo.User;
import com.shu.service.UserService;
import com.shu.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByEmail(String email) {
        User u=userMapper.findByEmail(email);
        return u;
    }

    @Override
    public void register(String userAccount,String userPassword,String nickName, Integer points,String email) {
        //加密处理
        String md5String= Md5Util.getMD5String(userPassword);
        //添加
        userMapper.add(userAccount, md5String,nickName, points, email);
    }

    @Override
    public void updatePassword(String userPassword) {

    }

    @Override
    public void updateInformaation(String userPassword, String nickName, String email) {

    }
}
