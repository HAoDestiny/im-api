package com.tlink.im.exception;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.log.Log;
import com.tlink.common.core.domain.R;
import com.tlink.common.core.exception.BaseException;
import com.tlink.common.core.exception.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author destiny
 * @date 2021/6/3 13:01
 */

@RestControllerAdvice
public class UnifiedExceptionHandler {

    private static final Log log = Log.get(UnifiedExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public R<?> baseException(BaseException e) {
        return R.fail(e.getDefaultMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail(HttpStatus.HTTP_NOT_FOUND, "path is not found");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return R.fail(HttpStatus.HTTP_BAD_METHOD, StrUtil.format("not support '{}' request", e.getMethod()));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public R<?> businessException(BusinessException e) {
        if (null == e.getCode()) {
            return R.fail(e.getMessage());
        }
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail("服务异常");
    }

}
