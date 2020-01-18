package com.qbank.qbank.entity;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王宇杰
 * @date 2020/1/9 12:44
 */
@Data
@Accessors(chain = true)
public class LogBook {

    /**
     * id
     */
    private String logbookId;
    /**
     * 操作
     */
    private String logbookOperation;
    /**
     * 操作者
     */
    private String logbookOperator;
    /**
     * 备注
     */
    private String logbookRemark;
    /**
     * 创建时间
     */
    private String logbookTime;

}
