package com.qbank.qbank.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 王宇杰
 * @date 2020/1/9 12:22
 */
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

    /**
     * default constructor
     */
    public User() {
    }

    /**
     * full constructor
     */
    public User(String userId, String userCase, String userWorkNumber, String userPassword,
                String userName, String userTitle, String userCollege, String userProfessionalField,
                String userPhoneNumber, String userMail, String userOffice, String userGrade,
                String userTime) {
        this.userId = userId;
        this.userCase = userCase;
        this.userWorkNumber = userWorkNumber;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userTitle = userTitle;
        this.userCollege = userCollege;
        this.userProfessionalField = userProfessionalField;
        this.userPhoneNumber = userPhoneNumber;
        this.userMail = userMail;
        this.userOffice = userOffice;
        this.userGrade = userGrade;
        this.userTime = userTime;
    }

    // Property accessors

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCase() {
        return this.userCase;
    }

    public void setUserCase(String userCase) {
        this.userCase = userCase;
    }

    public String getUserWorkNumber() {
        return this.userWorkNumber;
    }

    public void setUserWorkNumber(String userWorkNumber) {
        this.userWorkNumber = userWorkNumber;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTitle() {
        return this.userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserCollege() {
        return this.userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    public String getUserProfessionalField() {
        return this.userProfessionalField;
    }

    public void setUserProfessionalField(String userProfessionalField) {
        this.userProfessionalField = userProfessionalField;
    }

    public String getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserMail() {
        return this.userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserOffice() {
        return this.userOffice;
    }

    public void setUserOffice(String userOffice) {
        this.userOffice = userOffice;
    }

    public String getUserGrade() {
        return this.userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("userId", userId);
        object.put("userCase", userCase);
        object.put("userWorkNumber", userWorkNumber);
        object.put("userPassword", userPassword);
        object.put("userName", userName);
        object.put("userTitle", userTitle);
        object.put("userCollege", userCollege);
        object.put("userProfessionalField", userProfessionalField);
        object.put("userPhoneNumber", userPhoneNumber);
        object.put("userMail", userMail);
        object.put("userOffice", userOffice);
        object.put("userGrade", userGrade);
        object.put("userTime", userTime);
        return object.toString();
    }
}
