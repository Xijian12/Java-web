package com.shu.pojo;


import lombok.Data;

/**
 * @author 85131
 */
@Data
public class User {

    private Integer id;
    private String user_account;
    private String user_password;
    private String nickname;
    private Integer points;
    private String email; // 新增的邮箱字段



    public User(String account,String password,String nickname,String email){
        this.user_account=account;
        this.user_password=password;
        this.nickname=nickname;
        this.email=email;
        this.points=1;
    }


}

