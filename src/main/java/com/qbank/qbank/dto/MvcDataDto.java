package com.qbank.qbank.dto;

import com.alibaba.fastjson.JSONObject;
import com.qbank.qbank.entity.MyObj;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:46
 */
public class MvcDataDto implements MyObj {
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
    private MyObj resultObj;
    /**
     * 返回数据对象组(自定义)
     */
    private MyObj[] resultObjs;
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

    public MyObj getResultObj() {
        return resultObj;
    }

    public void setResultObj(MyObj resultObj) {
        this.resultObj = resultObj;
    }

    public MyObj[] getResultObjs() {
        return resultObjs;
    }

    public void setResultObjs(MyObj[] resultObjs) {
        this.resultObjs = resultObjs;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("resultCode", resultCode);
        object.put("resultMessage", resultMessage);
        object.put("resultObj", resultObj == null ? null : resultObj.toJson());
        object.put("redirectUrl", redirectUrl);
        return object;
    }
}
