package com.qbank.qbank.dto;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:46
 */
public class MvcDataDto {
    /**
     * 返回代码
     */
    private String resultCode;
    /**
     * 返回消息
     */
    private String resultMessage;
    /**
     * 返回数据对象(自定义)
     */
    private Object resultObj;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

}
