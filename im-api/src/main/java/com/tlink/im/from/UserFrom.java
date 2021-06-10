package com.tlink.im.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * @author destiny
 * @date 2021/5/31 10:32
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserFrom {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "0?(13|14|15|17|18|19)[0-9]{9}", message = "请输入正确的手机号")
    private String mobile;

    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 用户类型: 1-普通用户 2-商户 3-代理商
     */
    @Min(value = 1, message = "用户类型错误")
    @Max(value = 3, message = "用户类型错误")
    @NotNull(message = "用户类型不能为空")
    private Integer grade;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 特性标志位字段
     */
    private String feature;

    /**
     * 用户签名
     */
    private String motto;

    /**
     * 性别 0:女 1：男
     */
    private String gender;
}
