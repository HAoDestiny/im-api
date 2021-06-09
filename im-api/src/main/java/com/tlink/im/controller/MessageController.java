package com.tlink.im.controller;

import cn.hutool.core.bean.BeanUtil;
import com.tlink.common.core.constant.MessageConstants;
import com.tlink.common.core.domain.ApiResponse;
import com.tlink.common.core.domain.R;
import com.tlink.im.BaseController;
import com.tlink.im.from.MessageFrom;
import com.tlink.im.from.MessageListFrom;
import com.tlink.mode.base.BaseVo;
import com.tlink.mode.message.MessageVo;
import com.tlink.service.IMessageService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author destiny
 * @date 2021/6/4 13:20
 */

@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 发送消息
     *
     * @param messageFrom
     * @param bindingResult
     * @return
     */
    @PostMapping("/send")
    public String sendMessage(@RequestBody @Valid MessageFrom messageFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return response(R.fail(ApiResponse.PARAM_FAIL, bindingResult.getFieldErrors().get(0).getDefaultMessage()));
        }
        MessageVo messageVo = new MessageVo();
        BeanUtil.copyProperties(messageFrom, messageVo);
        return response(messageService.sendMessage(
                messageFrom.getToken(),
                messageFrom.getAccount(),
                messageVo
        ));
    }

    /**
     * 获取会话记录列表
     *
     * @param messageFrom
     * @param bindingResult
     * @return
     */
    @PostMapping("/conversation/list")
    public String list(@RequestBody @Valid MessageFrom messageFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return response(R.fail(ApiResponse.PARAM_FAIL, bindingResult.getFieldErrors().get(0).getDefaultMessage()));
        }

        return response(messageService.getConversationList(
                messageFrom.getAccount(),
                messageFrom.getTimestamp()
        ));
    }

    /**
     * 获取会话消息记录列表
     *
     * @param messageListFrom
     * @param bindingResult
     * @return
     */
    @PostMapping("/list")
    public String list(@RequestBody @Valid MessageListFrom messageListFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return response(R.fail(ApiResponse.PARAM_FAIL, bindingResult.getFieldErrors().get(0).getDefaultMessage()));
        }

        return response(messageService.getMessageList(
                getPage(messageListFrom.getPageCode(), messageListFrom.getPageSize()),
                messageListFrom.getAccount(),
                messageListFrom.getFriendAccount(),
                messageListFrom.getTimestamp()
        ));
    }

    @PostMapping("/offline/list")
    public String offlineList(@RequestBody @Valid BaseVo baseVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return response(R.fail(ApiResponse.PARAM_FAIL, bindingResult.getFieldErrors().get(0).getDefaultMessage()));
        }

        return response(messageService.getOfflineMessageList(
                getPage(baseVo.getPageCode(), baseVo.getPageSize()),
                baseVo.getAccount(),
                baseVo.getTimestamp()
        ));
    }

    /**
     * 更新消息状态
     * @param messageId
     * @param action ${@link MessageConstants (ACTION_KEY_READ, ACTION_KEY_READ) }
     * @return
     */
    @PostMapping("/update/{action}/{messageId}")
    public String updateMessageState(@PathVariable("action") String action, @PathVariable("messageId") Long messageId) {
        return response(messageService.updateMessageState(messageId, action));
    }
}
