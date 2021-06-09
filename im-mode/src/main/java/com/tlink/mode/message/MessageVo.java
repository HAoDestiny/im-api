package com.tlink.mode.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author destiny
 * @date 2021/6/4 12:53
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class MessageVo {

    private Long id;

    /**
     * 操作类型
     */
    private String action;

    /**
     * 消息内容
     */
    private String content;

    private String extra;

    /**
     * 消息类型
     */
    private String format;

    /**
     * 接收者
     */
    private String receiver;

    /**
     * 发送者
     */
    private String sender;

    /**
     * 消息状态
     */
    private String state;

    /**
     * 标题
     */
    private String title;

    private Long timestamp;
}
