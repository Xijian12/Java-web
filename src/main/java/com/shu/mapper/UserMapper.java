package com.shu.mapper;

import com.shu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 85131
 */
@Mapper
public interface UserMapper {


//@Insert("Insert into user(account, password, nickname, points, email) " +
//        "values(#{account}, #{password}, #{nickname}, #{point}, #{email})")
//void add(@Param("account") String account, @Param("password") String password,
//         @Param("nickname") String nickname, @Param("point") Integer point,
//         @Param("email") String email);
    void add(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword,
                          @Param("nickName") String nickName, @Param("points") Integer points,
                          @Param("email") String email);//用户注册信息添加



//    @Select("select * from user where email=#{email}")
//    User findByEmail(@Param("email")String email);

    User findByEmail(@Param("email")String email);//查找用户


}
