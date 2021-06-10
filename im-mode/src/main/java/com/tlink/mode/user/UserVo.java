package com.tlink.mode.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author destiny
 * @date 2021/5/31 10:32
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Long id;
    private String account;
    private String name;
    private String telephone;
    private String email;
    private String code;
    private String gender;
    private Integer grade;
    private String feature;
    private String motto;
    /**
     * 用户状态 0-正常 1-禁用
     */
    private String state;
    private long timestamp;
    private boolean disabled;

}
