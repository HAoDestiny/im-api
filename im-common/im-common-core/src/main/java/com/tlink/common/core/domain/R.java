package com.tlink.common.core.domain;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tlink.common.core.constant.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

/**
 * 响应信息主体
 *
 * @author destiny
 */
public class R<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    private int code;

    private String msg;

    private T data;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> R<T> ok(Collection<T> list) {
        JSONObject res = new JSONObject();
        res.put("list", list);
        return (R<T>) restResult(res, SUCCESS, null);
    }

    public static <T> R<T> ok(IPage<T> page) {
        JSONObject res = new JSONObject();
        res.put("list", page.getRecords());
        res.put("pageCode", page.getCurrent());
        res.put("pageSize", page.getSize());
        res.put("total", page.getTotal());
        res.put("totalPages", page.getPages());
        return (R<T>) restResult(res, SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> R<T> fail(ApiResponse response) {
        return restResult(null, Integer.parseInt(response.getCode()), response.getMsg());
    }

    public static <T> R<T> fail(ApiResponse response, String msg) {
        return restResult(null, Integer.parseInt(response.getCode()), msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
