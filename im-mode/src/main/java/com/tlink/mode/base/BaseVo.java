package com.tlink.mode.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author destiny
 * @date 2021/6/4 13:21
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BaseVo {

    @NotBlank(message = "未登录")
    private String token;

    /**
     * 账号 唯一标识
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 当前页
     */
    private Integer pageCode;

    /**
     * 每页大小
     */
    private Integer pageSize;

    private Long timestamp;
}
