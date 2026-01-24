package com.shu.mapper;

import com.shu.entity.vo.request.user.EmailVerifyCodeVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmailVerifyCodeMapper {
    @Select("SELECT * FROM email_verify_code WHERE email = #{email}")
    EmailVerifyCodeVO selectVerifyCodeByEmail(@Param("email") String email);

    void insertOrUpdateVerifyCode(EmailVerifyCodeVO emailVerifyCodeVO);

    @Delete("DELETE from email_verify_code WHERE email = #{email}")
    void deleteVerifyCodeByEmail(@Param("email") String email);

    @Delete("DELETE FROM email_verify_code WHERE expire_time < NOW()")
    int deleteVerifyCodeExpired();
}
