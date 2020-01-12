package com.qbank.qbank.entity;


import com.alibaba.fastjson.JSONObject;

/**
 * @author 王宇杰
 * @date 2020/1/9 12:44
 */
public class LogBook implements MyObj {

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

    /**
     * default constructor
     */
    public LogBook() {
    }

    /**
     * full constructor
     */
    public LogBook(String logbookId, String logbookOperation, String logbookOperator,
                   String logbookRemark, String logbookTime) {
        this.logbookId = logbookId;
        this.logbookOperation = logbookOperation;
        this.logbookOperator = logbookOperator;
        this.logbookRemark = logbookRemark;
        this.logbookTime = logbookTime;
    }

    // Property accessors

    public String getLogbookId() {
        return logbookId;
    }

    public void setLogbookId(String logbookId) {
        this.logbookId = logbookId;
    }

    public String getLogbookOperation() {
        return logbookOperation;
    }

    public void setLogbookOperation(String logbookOperation) {
        this.logbookOperation = logbookOperation;
    }

    public String getLogbookOperator() {
        return logbookOperator;
    }

    public void setLogbookOperator(String logbookOperator) {
        this.logbookOperator = logbookOperator;
    }

    public String getLogbookRemark() {
        return logbookRemark;
    }

    public void setLogbookRemark(String logbookRemark) {
        this.logbookRemark = logbookRemark;
    }

    public String getLogbookTime() {
        return logbookTime;
    }

    public void setLogbookTime(String logbookTime) {
        this.logbookTime = logbookTime;
    }

    @Override
    public JSONObject toJson() {
        //TODO ToJson
        return null;
    }

}
