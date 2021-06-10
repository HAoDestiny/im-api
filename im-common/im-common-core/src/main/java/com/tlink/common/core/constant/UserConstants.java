package com.tlink.common.core.constant;

/**
 * @author destiny
 * @date 2021/6/4 12:59
 */
public interface UserConstants {

    /**
     * 用户标识
     */
    String ACCOUNT = "USER-ACCOUNT";

    /**
     * 用户状态
     */
    String USER_STATE_NORMAL = "0";
    String USER_STATE_DEL = "1";

    /**
     * 用户性别
     */
    String USER_GENDER_GIRL = "0";
    String USER_GENDER_BOY = "1";

    /**
     * 用户类型 级别
     */
    Integer USER_GRADE_COMMON = 1;
    Integer USER_GRADE_MERCHANT = 2;
    Integer USER_GRADE_PROXY = 3;
}
