package com.tlink.mode.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author destiny
 * @date 2021/6/4 12:50
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class MessageConversationVo {

    /**
     * 会话方
     */
    private String account;

    /**
     * 未读消息
     */
    private Long messageCount;
}
