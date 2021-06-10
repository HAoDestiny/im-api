package com.tlink.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tlink.common.core.domain.R;
import com.tlink.dao.domain.User;

public interface IUserService extends IService<User> {

    /**
     * 添加用户
     * @param user
     * @return
     */
    R<?> register(User user);

}
