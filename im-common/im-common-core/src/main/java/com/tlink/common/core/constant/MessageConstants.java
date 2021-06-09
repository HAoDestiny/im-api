package com.tlink.common.core.constant;

public interface MessageConstants {
    String MESSAGE_STATE_NOT_RECEIVE = "0";
    String MESSAGE_STATE_RECEIVED = "1";
    String MESSAGE_STATE_READ = "2";

    /**
     * 操作类型 received = 接收消息， read = 已读消息
     */
    String ACTION_KEY_READ = "read";
    String ACTION_KEY_RECEIVED = "received";
}
