package com.tlink.im.from;

import cn.hutool.core.date.DateUtil;
import com.tlink.mode.base.BaseVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author destiny
 * @date 2021/6/7 13:20
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class MessageListFrom extends BaseVo {

    /**
     * 账号 唯一标识
     */
    @NotBlank(message = "对方账号不能为空")
    private String friendAccount;
}
