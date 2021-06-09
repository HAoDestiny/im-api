package com.tlink.im;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlink.mode.base.BaseVo;

/**
 * @author destiny
 * @date 2021/6/3 13:17
 */
public class BaseController {

    protected String response(Object object) {
        return null == object ? null : JSON.toJSONString(object);
    }

    protected <T> Page<T> getPage(BaseVo baseVo) {
        return getPage(baseVo.getPageCode(), baseVo.getPageSize());
    }

    protected <T> Page<T> getPage(Integer pageCode, Integer pageSize) {
        if (null == pageCode || pageCode < 0) {
            pageCode = 0;
        }

        if (null == pageSize || pageSize <= 0) {
            pageSize = 10;
        }
        return new Page<T>(pageCode, pageSize);
    }
}
