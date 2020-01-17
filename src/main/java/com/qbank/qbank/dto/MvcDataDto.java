package com.qbank.qbank.dto;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:46
 */
public class MvcDataDto {
    /**
     * 成功
     */
    public static final String SUCCESS = "success";
    /**
     * 失败
     */
    public static final String FAIL = "fail";
    /**
     * 错误
     */
    public static final String ERROR = "error";
    /**
     * 其它
     */
    public static final String OTHER = "other";
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
    /**
     * 返回后跳转页面
     */
    private String redirectUrl;

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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("resultCode", resultCode);
        object.put("resultMessage", resultMessage);
        object.put("resultObj", resultObj == null ? null : resultObj.toString());
        object.put("redirectUrl", redirectUrl);
        return object.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MvcDataDto that = (MvcDataDto) o;
        return resultCode.equals(that.resultCode) &&
                resultMessage.equals(that.resultMessage) &&
                resultObj.equals(that.resultObj) &&
                redirectUrl.equals(that.redirectUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, resultMessage, resultObj, redirectUrl);
    }
}
