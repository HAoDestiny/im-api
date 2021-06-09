package com.tlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tlink.dao.domain.Message;
import com.tlink.mode.message.MessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity generator.domain.Message
 */

public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 获取会话记录
     * @param account
     * @param startTime
     * @return
     */
    List<MessageVo> selectConversationByAccount(@Param("account") String account, @Param("startTime") Long startTime);

    /**
     * 获取会话消息记录
     * @param account
     * @param startTime
     * @return
     */
    IPage<MessageVo> selectMessageListBySenderAndReceiver(IPage<Message> page,
                                                      @Param("sender") String account,
                                                      @Param("receiver") String friendAccount,
                                                      @Param("startTime") Long startTime);

    /**
     * 查询当前用户相应状态消息记录
     * @param page
     * @param receiver
     * @param state
     * @param startTime
     * @return
     */
    IPage<MessageVo> selectMessageListByReceiverAndState(IPage<Message> page,
                                                          @Param("receiver") String receiver,
                                                          @Param("state") String state,
                                                          @Param("startTime") Long startTime);
}




