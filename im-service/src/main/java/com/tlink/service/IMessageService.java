package com.tlink.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tlink.common.core.constant.MessageConstants;
import com.tlink.common.core.domain.R;
import com.tlink.dao.domain.Message;
import com.tlink.mode.message.MessageConversationVo;
import com.tlink.mode.message.MessageVo;

import java.util.List;

public interface IMessageService extends IService<Message> {

    List<Message> queryAll();

    IPage<Message> queryMessage(Page<Message> page);


    /**
     * 获取用户详情
     *
     * @param imToken gateway token
     * @param account im用户 唯一
     * @return
     */
    R<?> getImUserInfo(String imToken, String account);

    /**
     * 发送消息
     * @param imToken
     * @param account
     * @return
     */
    R<?> sendMessage(String imToken, String account, MessageVo messageVo);

    /**
     * 获取会话列表
     *
     * @param account
     * @param endTime
     * @return
     */
    R<?> getConversationList(String account, Long endTime);

    /**
     * 获取会话消息列表
     * @param page
     * @param account 当前账号
     * @param friendAccount 对方账号
     * @param timestamp
     * @return
     */
    R<?> getMessageList(Page<Message> page, String account, String friendAccount, Long timestamp);

    /**
     * 获取离线消息
     * @param page
     * @param account
     * @param timestamp
     * @return
     */
    R<?> getOfflineMessageList(Page<Message> page, String account, Long timestamp);

    /**
     * 更新消息状态
     * @param messageId
     * @param action ${@link MessageConstants}
     * @return
     */
    R<?> updateMessageState(Long messageId, String action);
}
