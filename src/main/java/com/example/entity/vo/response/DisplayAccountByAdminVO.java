package com.example.entity.vo.response;

import com.example.entity.dto.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class DisplayAccountByAdminVO {
    @Email
    String email;
    @Length(max = 6, min = 1)
    String id;
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$")
    @Length(min = 1, max = 10)
    String username;
    @Length(min = 6, max = 20)
    String password;
    @Length(min = 6, max = 20)
    String role;
    @Length(min = 1,max=10)
    Integer points;
    @Length(min = 15,max = 30)
    String pictureUrl;

    public DisplayAccountByAdminVO(Account account) {
        this.email = account.getEmail();
        this.id = account.getId().toString();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.role = account.getRole();
        this.points = account.getPoints();
        this.pictureUrl = account.getPictureUrl();
    }



}
