package com.shu.entity.vo.response;

import com.shu.entity.dto.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DisplayAccountByUserVO {
    @Email
    String email;
    @Length(max = 6, min = 1)
    String id;
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$")
    @Length(min = 1, max = 10)
    String username;
    @Length(min = 6, max = 20)
    String role;
    @Length(min = 1,max=10)
    Integer points;
    @Length(min = 15,max = 30)
    String avatarUrl;
    @Length(min = 15,max = 30)
    String avatarUuid;

    @Override
    public String toString() {
        return  "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", points=" + points +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", avatarUUID='" + avatarUuid + '\'' +
                '}';
    }

    public DisplayAccountByUserVO(Account account) {
        this.email = account.getEmail();
        this.id = account.getId().toString();
        this.username = account.getUsername();
        this.role = account.getRole();
        this.points = account.getPoints();
        this.avatarUrl = account.getAvatarUrl();
        this.avatarUuid= account.getAvatarUuid();
    }


}
