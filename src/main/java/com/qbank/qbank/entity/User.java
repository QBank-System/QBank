package com.qbank.qbank.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王宇杰
 * @date 2020/1/9 12:22
 */
@Data
@Accessors(chain = true)
public class User {

    public static final String NORMAL = "2";
    public static final String ADMIN = "1";
    public static final String SUPERADMIN = "0";

    /**
     * id
     */
    private String userId;
    /**
     * 头像
     */
    private String userCase;
    /**
     * 学工号
     */
    private String userWorkNumber;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 职称
     */
    private String userTitle;
    /**
     * 所属学院
     */
    private String userCollege;
    /**
     * 专业领域
     */
    private String userProfessionalField;
    /**
     * 电话号码
     */
    private String userPhoneNumber;
    /**
     * 邮箱地址
     */
    private String userMail;
    /**
     * 办公地址
     */
    private String userOffice;
    /**
     * 功能权限
     */
    private String userGrade;
    /**
     * 用户创建时间
     */
    private String userTime;

}
