package com.shu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shu.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author 85131
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    @Update("update db_account set points = #{points} where email = #{userEmail}")
    void updateUserPoints(String userEmail, Integer points);
}
