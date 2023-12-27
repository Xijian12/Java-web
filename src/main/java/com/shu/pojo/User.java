package com.shu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 85131
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String userAccount;
    private String userPassword;
    private String nickName;
    private Integer points;
    private String email; // 新增的邮箱字段



    public User(String userAccount,String userPassword,String nickName,String email){
        this.userAccount=userAccount;
        this.userPassword=userPassword;
        this.nickName=nickName;
        this.email=email;
        this.points=1;
    }


}

