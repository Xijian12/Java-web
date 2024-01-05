package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.entity.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 数据库中的用户信息
 */
@Data
@TableName("db_account")
@AllArgsConstructor
@NoArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    String email;
    String role;
    Date registerTime;
    Integer points;
    String avatarUrl;
    String avatarUuid;

    public Account(Integer id, String username, String password, String email, String role, Date registerTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registerTime = registerTime;
        // 默认值或null初始化
        this.points = 5;
        this.avatarUrl = null; // 或者任何默认URL
        this.avatarUuid= null; // 或者任何默认UUID
    }

    public Account(Account another) {
        this.id = another.id;
        this.username = another.username;
        this.password = another.password;
        this.email = another.email;
        this.role = another.role;
        this.registerTime = another.registerTime;
        // 对于没有的字段，可以选择默认值或者直接使用null
        this.points = another.points != null ? another.points : 5;
        this.avatarUrl = another.avatarUrl!= null ? another.avatarUrl : null; // 或者默认URL
        this.avatarUuid = another.avatarUuid!= null ? another.avatarUuid : null; // 或者默认URL
    }







}
