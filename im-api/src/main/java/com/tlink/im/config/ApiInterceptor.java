package com.tlink.im.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.tlink.common.core.constant.Constants;
import com.tlink.common.core.constant.UserConstants;
import com.tlink.common.core.domain.ApiResponse;
import com.tlink.common.core.domain.R;
import com.tlink.redis.service.RedisService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author destiny
 * @date 2021/6/4 13:28
 */
//@Configuration
//@ConfigurationProperties(prefix = "im.token")
public class ApiInterceptor extends HandlerInterceptorAdapter {

    private static final Log log = Log.get(ApiInterceptor.class);

    private String header;

    private final RedisService redisService;

    public ApiInterceptor(RedisService redisService) {
        this.redisService = redisService;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.debug("request: {}", request.getRequestURL().toString());

        String token = request.getHeader(header);

        if (StrUtil.isBlank(token)) {
            responseError(response, ApiResponse.NOT_LOGIN);
            return false;
        }

        String account = redisService.getCacheObject(token).toString();
        if (StrUtil.isBlank(account)) {
            responseError(response, ApiResponse.TOKEN_FAIL);
            return false;
        }

        request.setAttribute(UserConstants.ACCOUNT, account);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private void responseError(HttpServletResponse response, ApiResponse apiResponse) {
        response.setContentType("application/json; charset=utf8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(JSON.toJSONString(R.fail(apiResponse)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
