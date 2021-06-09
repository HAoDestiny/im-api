package com.tlink.im.from;

import com.tlink.mode.base.BaseVo;
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
public class MessageFrom extends BaseVo {

    /**
     * 接收者
     */
    @NotBlank(message = "接收者不能为空")
    private String receiver;

//    /**
//     * 发送者
//     */
//    private String sender;

    /**
     * 消息类型
     */
    @NotBlank(message = "消息类型不能为空")
    private String action;

    /**
     * 消息文字内容
     */
    @NotBlank(message = "发送内容不能为空")
    private String content;

    private String extra;

    /**
     * 0:文字1：图片，2：语音,3 文件  4:地图
     */
    @NotBlank(message = "消息格式不能为空")
    private String format;

    private String title;

    private Long timestamp;
}
