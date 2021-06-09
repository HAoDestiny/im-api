package com.tlink.mode.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author destiny
 * @date 2021/5/31 11:56
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BasePageVo {
    /**
     * 当前记录数
     */
    private int count;
    /**
     * 每页大小
     */
    private int size;
    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 总页数
     */
    private int countPage;
}
