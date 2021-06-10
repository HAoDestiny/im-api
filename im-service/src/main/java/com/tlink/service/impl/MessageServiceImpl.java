package com.tlink.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlink.common.core.constant.MessageConstants;
import com.tlink.common.core.domain.ApiResponse;
import com.tlink.common.core.domain.R;
import com.tlink.dao.domain.Message;
import com.tlink.dao.mapper.MessageMapper;
import com.tlink.mode.IMBaseResponse;
import com.tlink.mode.user.UserVo;
import com.tlink.mode.message.MessageConversationVo;
import com.tlink.mode.message.MessageVo;
import com.tlink.service.IMessageService;
import com.tlink.service.client.IMClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    private static final int MIN_DATE_AGO = -30;
    private final IMClient imClient;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(IMClient imClient, MessageMapper messageMapper) {
        this.imClient = imClient;
        this.messageMapper = messageMapper;
    }

    @Override
    public List<Message> queryAll() {
        return messageMapper.selectList(new QueryWrapper<Message>().lambda().select());
    }

    @Override
    public IPage<Message> queryMessage(Page<Message> page) {
        page.setSearchCount(false);
        return messageMapper.selectPage(page, new QueryWrapper<Message>().lambda().select());
    }

    @Override
    public R<?> sendMessage(String imToken, String account, MessageVo messageVo) {

        if (account.equals(messageVo.getReceiver())) {
            return R.fail(ApiResponse.PARAM_FAIL, "参数错误, 发送者与接收者不能相同");
        }

        messageVo.setSender(account);

        IMBaseResponse baseResponse = imClient.sendMessage(imToken, account, messageVo);
        if (null == baseResponse) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        if (!baseResponse.isOk()) {
            if (StrUtil.isNotBlank(baseResponse.getMessage())) {
                R.fail(ApiResponse.PARAM_FAIL, baseResponse.getMessage());
            }
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        return R.ok("发送成功");
    }

    @Override
    public R<?> getImUserInfo(String imToken, String account) {
        IMBaseResponse baseResponse = imClient.getUserInfo(imToken, account);
        if (null == baseResponse) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        if (!baseResponse.isOk()) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        JSONObject data = baseResponse.getData();
        if (null == data) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        return R.ok(JSON.parseObject(JSON.toJSONString(data), UserVo.class));
    }

    /**
     * v 1.0 需求 暂时通过最近消息记录初始化会话列表
     *
     * @param account
     * @param endTime 默认最近 30天
     * @return
     */
    @Override
    public R<?> getConversationList(String account, Long endTime) {

        // 最近30天
        long afterTime = DateUtil.offsetDay(DateUtil.date(), MIN_DATE_AGO).getTime();
        if (null == endTime || endTime < afterTime) {
            endTime = afterTime;
        }

        List<MessageVo> messageVoList = messageMapper.selectConversationByAccount(account, endTime);
        if (CollUtil.isEmpty(messageVoList)) {
            return R.ok(new ArrayList<>());
        }

        messageVoList = messageVoList
                .stream()
                .sorted(Comparator.comparing(MessageVo::getTimestamp, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        Set<String> conversation = new HashSet<>();
        Set<String> senders = messageVoList.stream().collect(Collectors.groupingBy(MessageVo::getSender)).keySet();
        Set<String> receivers = messageVoList.stream().collect(Collectors.groupingBy(MessageVo::getReceiver)).keySet();
        conversation.addAll(senders);
        conversation.addAll(receivers);

        List<String> conversations = conversation.stream().filter(r -> !r.equals(account)).collect(Collectors.toList());
        if (CollUtil.isEmpty(conversations)) {
            return R.ok(new ArrayList<>());
        }

        List<MessageConversationVo> messageConversationVos = new ArrayList<>();
        conversations.forEach(r -> {
            MessageConversationVo messageConversationVo = new MessageConversationVo();
            messageConversationVo.setAccount(r);
            messageConversationVos.add(messageConversationVo);
        });
        return R.ok(messageConversationVos);
    }

    @Override
    public R<?> getMessageList(Page<Message> page, String account, String friendAccount, Long endTime) {
        // 最近30天
        long afterTime = DateUtil.offsetDay(DateUtil.date(), MIN_DATE_AGO).getTime();
        if (null == endTime || endTime < afterTime) {
            endTime = afterTime;
        }
        page.setSearchCount(false);
        IPage<MessageVo> messageVoIPage = messageMapper.selectMessageListBySenderAndReceiver(page, account, friendAccount, endTime);

        List<MessageVo> messageVos = messageVoIPage.getRecords();
        if (CollUtil.isNotEmpty(messageVos)) {
            messageVoIPage.setRecords(messageVos
                    .stream()
                    .sorted(Comparator.comparing(MessageVo::getTimestamp))
                    .collect(Collectors.toList())
            );
        }
        return R.ok(messageVoIPage);
    }

    @Override
    public R<?> getOfflineMessageList(Page<Message> page, String account, Long endTime) {
        // 最近30天
        long afterTime = DateUtil.offsetDay(DateUtil.date(), MIN_DATE_AGO).getTime();
        if (null == endTime || endTime < afterTime) {
            endTime = afterTime;
        }

        page.setSearchCount(false);
        IPage<MessageVo> messageVoIPage = messageMapper.selectMessageListByReceiverAndState(
                page,
                account,
                MessageConstants.MESSAGE_STATE_NOT_RECEIVE,
                endTime
        );
        return R.ok(messageVoIPage);
    }

    @Override
    public R<?> updateMessageState(Long messageId, String action) {

        if (null == messageId || messageId <= 0) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        if (!MessageConstants.ACTION_KEY_READ.equals(action) && !MessageConstants.ACTION_KEY_RECEIVED.equals(action)) {
            return R.fail(ApiResponse.PARAM_FAIL);
        }

        Message message = messageMapper.selectOne(new QueryWrapper<Message>().select("id", "state").lambda().eq(Message::getId, messageId));
        if (null == message || null == message.getId() || message.getId() <= 0) {
            return R.fail(ApiResponse.NOT_FOUND);
        }

        if (MessageConstants.ACTION_KEY_RECEIVED.equals(action)) {
            // 已接收
            if (!MessageConstants.MESSAGE_STATE_NOT_RECEIVE.equals(message.getState())) {
                return R.ok();
            }
            message.setState(MessageConstants.MESSAGE_STATE_RECEIVED);
        }

        if (MessageConstants.ACTION_KEY_READ.equals(action)) {
            // 已读
            if (MessageConstants.MESSAGE_STATE_READ.equals(message.getState())) {
                return R.ok();
            }
            message.setState(MessageConstants.MESSAGE_STATE_READ);
        }

        int i = messageMapper.updateById(message);
        if (i < 1) {
            return R.fail(ApiResponse.ACTION_FAIL);
        }
        return R.ok();
    }
}




