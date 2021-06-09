package com.tlink.mode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tlink.common.core.constant.IMConstants;
import com.tlink.mode.base.BasePageVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author destiny
 * @date 2021/5/31 11:01
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class IMBaseResponse {

    private Integer code;
    private String message;
    private JSONObject data;
    private JSONArray dataList;
    private BasePageVo page;

    public boolean isOk() {
        return IMConstants.RESPONSE_SUCCESS_CODE.equals(this.code);
    }
}
