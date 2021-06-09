package com.tlink.service.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.tlink.mode.IMBaseResponse;
import com.tlink.mode.message.MessageVo;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * @author destiny
 * @date 2021/5/31 10:29
 */

public class IMClient {

    private static final Log log = Log.get(IMClient.class);

    private String url;
    private static final String HEADER_TOKEN_KEY = "access-token";

    enum URI {
        URI_SEND_MESSAGE("{}/api/message/send", "POST"),
        URI_USER_INFO("{}/api/user/{}", "GET"),
        URI_USER_MESSAGE_LIST("{}/api/message/list/{}/{}", "GET"),
        URI_USER_MESSAGE_OFFLINE("{}/api/message/list/offline", "GET"),
        URI_USER_MESSAGE_RECEIVED("{}/api/message/received/{}", "GET"),
        URI_USER_MESSAGE_RECEIVED_BATCH("{}/api/message/received/batch", "GET"),
        URI_USER_MESSAGE_READ("{}/api/message/read/{}", "GET"),
        URI_USER_MESSAGE_REVOKE("{}/api/message/revoke/{}", "GET"),
        ;

        private String uri;
        private String method;

        URI(String uri, String method) {
            this.uri = uri;
            this.method = method;
        }

        public String getMethod() {
            return method;
        }

        public String getUri() {
            return uri;
        }
    }

    public IMClient(String url) {
        this.url = url;
    }

    public IMBaseResponse sendMessage(final String token, final String account, MessageVo messageVo) {
        if (StrUtil.isBlank(token) || StrUtil.isBlank(account)) {
            return null;
        }

        return request(URI.URI_SEND_MESSAGE, token, messageVo, account);
    }

    /**
     * 获取IM用户详情
     * @param account 用户唯一标识 (用户名称/手机号)
     * @return
     */
    public IMBaseResponse getUserInfo(final String token, final String account) {
        if (StrUtil.isBlank(token) || StrUtil.isBlank(account)) {
            return null;
        }
        return request(URI.URI_USER_INFO, token, null, account);
    }

    /**
     * 获取消息列表
     * @param token
     * @param account
     * @param page
     * @return
     */
    public IMBaseResponse getUserMessages(final String token, final String account, long page) {
        if (StrUtil.isBlank(token) || StrUtil.isBlank(account)) {
            return null;
        }
        return request(URI.URI_USER_MESSAGE_LIST, token, null, account, page);
    }

    /**
     * 获取离线消息列表
     * @param token
     * @return
     */
    public IMBaseResponse getUserOfflineMessages(final String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return request(URI.URI_USER_MESSAGE_OFFLINE, token, null);
    }

    /**
     * 消息接收
     * @param token
     * @param messageId
     * @return
     */
    public IMBaseResponse receivedMessage(final String token, final Long messageId) {
        if (StrUtil.isBlank(token) || null == messageId || messageId <= 0) {
            return null;
        }
        return request(URI.URI_USER_MESSAGE_RECEIVED, token, null, messageId);
    }

    /**
     * 消息接收 - 批量
     * @param token
     * @return
     */
    public IMBaseResponse receivedMessageBatch(final String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return request(URI.URI_USER_MESSAGE_RECEIVED_BATCH, token, null);
    }

    /**
     * 消息读取
     * @param token
     * @param messageId
     * @return
     */
    public IMBaseResponse readMessage(final String token, final Long messageId) {
        if (StrUtil.isBlank(token) || null == messageId || messageId <= 0) {
            return null;
        }
        return request(URI.URI_USER_MESSAGE_READ, token, null, messageId);
    }

    protected IMBaseResponse request(final URI URi, final String token, final Object bodyParams, final Object ...restful) {
        String url = formatURL(URi, restful);
        log.debug("request url: {}", url);
        if ("GET".equals(URi.method)) {
            String body = get(url, token, bodyParams, 10000)
                    .execute()
                    .body();

            return getResponse(body);
        }

        if ("POST".equals(URi.method)) {
            String body = post(url, token, bodyParams, 10000)
                    .execute()
                    .body();

            return getResponse(body);
        }

        return null;
    }

    protected HttpRequest get(String url, String token, Object paramMap, int timeout) {
        return HttpRequest
                .get(url)
                .form(BeanUtil.beanToMap(paramMap))
                .timeout(timeout)
                .header(HEADER_TOKEN_KEY, token);
    }

    protected HttpRequest post(String url, String token, Object paramMap, int timeout) {
        return HttpRequest
                .post(url)
                .form(BeanUtil.beanToMap(paramMap))
                .timeout(timeout)
                .header(HEADER_TOKEN_KEY, token);
    }

    private IMBaseResponse getResponse(String body) {
        log.debug("response: {}", body);
        return JSON.parseObject(body, IMBaseResponse.class);
    }

    private String formatURL(URI uri, Object ...params) {
        return StrUtil.format(StrUtil.format(uri.uri, url), params);
    }

    public static void main(String[] args) {
        IMClient imClient = new IMClient("http://127.0.0.1:9090");
        imClient.getUserInfo("c77d193b4c1b7fedd81969dacd09964f", "destiny");
        imClient.getUserMessages("c77d193b4c1b7fedd81969dacd09964f", "destiny", 0);
        imClient.receivedMessageBatch("c77d193b4c1b7fedd81969dacd09964f");
        imClient.getUserOfflineMessages("c77d193b4c1b7fedd81969dacd09964f");
    }
}
