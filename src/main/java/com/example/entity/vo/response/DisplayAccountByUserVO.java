package com.example.entity.vo.response;

import com.example.entity.dto.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class DisplayAccountByUserVO {
    @Email
    String email;
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$")
    @Length(min = 1, max = 10)
    String username;
    @Length(min = 6, max = 20)
    String password;
    @Length(min = 1,max=10)
    Integer points;
    @Length(min = 15,max = 30)
    String pictureUrl;

    public DisplayAccountByUserVO(Account account) {
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.points = account.getPoints();
        this.pictureUrl = account.getPictureUrl();
    }
}
