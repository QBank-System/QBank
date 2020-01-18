package com.qbank.qbank.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:46
 */
@Data
@Accessors(chain = true)
public class MvcDataDto {
    /**
     * 成功
     */
    public static final int SUCCESS = 0;
    /**
     * 失败
     */
    public static final int FAIL = -1;
    /**
     * 错误
     */
    public static final int ERROR = -2;
    /**
     * 其它
     */
    public static final int OTHER = 1;
    /**
     * 返回代码
     */
    private int code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据对象(自定义)
     */
    private Object data;
    /**
     * 数量
     */
    private int count;
    /**
     * 返回后跳转页面
     */
    private String url;
}
