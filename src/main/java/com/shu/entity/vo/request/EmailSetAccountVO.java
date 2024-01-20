package com.shu.entity.vo.request;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class EmailSetAccountVO {
    @Email
    String email;
    @Length(max = 6, min = 6)
    String code;
    @Length(min = 6, max = 20)
    String password;

}
