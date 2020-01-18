package com.qbank.qbank.entity;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王宇杰
 * @date 2020/1/9 12:44
 */
@Data
@Accessors(chain = true)
public class Questions  {

    /**
     * id
     */
    private String questionId;
    /**
     * 类别，维护到数据字典
     */
    private String questionClass;
    /**
     * 题干
     */
    private String questionContext;
    /**
     * 答案
     */
    private String questionAnswer;
    /**
     * 创建者
     */
    private String questionCreator;
    /**
     * 是否私有
     */
    private String questionPrivate;
    /**
     * 创建时间
     */
    private String questionTime;


}
