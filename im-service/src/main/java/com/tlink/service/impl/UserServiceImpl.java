package com.tlink.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlink.common.core.constant.UserConstants;
import com.tlink.common.core.domain.ApiResponse;
import com.tlink.common.core.domain.R;
import com.tlink.dao.domain.User;
import com.tlink.dao.mapper.UserMapper;
import com.tlink.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public R<?> register(User user) {

        if (!user.getGrade().equals(UserConstants.USER_GRADE_COMMON) &&
                !user.getGrade().equals(UserConstants.USER_GRADE_MERCHANT) &&
                !user.getGrade().equals(UserConstants.USER_GRADE_PROXY)) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        Long ing = userMapper.checkAccount(user.getAccount());
        if (null != ing) {
            return R.fail(ApiResponse.ACCOUNT_EXISTED);
        }

        if (StrUtil.isBlank(user.getGender())) {
            user.setGender(UserConstants.USER_GENDER_BOY);
        }
        user.setPassword(SecureUtil.md5(String.valueOf(user.getAccount().hashCode())));
        user.setState(UserConstants.USER_STATE_NORMAL);
        user.setTimestamp(DateUtil.current());
        int insert = userMapper.insert(user);
        if (insert < 1) {
            return R.fail("注册失败");
        }

        return R.ok("注册成功");
    }
}




