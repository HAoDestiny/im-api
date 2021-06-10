package com.tlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tlink.dao.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    Long checkAccount(@Param("account") String account);

}




