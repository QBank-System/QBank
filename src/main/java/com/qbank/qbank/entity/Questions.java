package com.qbank.qbank.entity;


/**
 * @author 王宇杰
 * @date 2020/1/9 12:44
 */
public class Questions {

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

    /**
     * default constructor
     */
    public Questions() {
    }

    /**
     * full constructor
     */
    public Questions(String questionId, String questionClass, String questionContext,
                     String questionAnswer, String questionCreator, String questionPrivate,
                     String questionTime) {
        this.questionId = questionId;
        this.questionClass = questionClass;
        this.questionContext = questionContext;
        this.questionAnswer = questionAnswer;
        this.questionCreator = questionCreator;
        this.questionPrivate = questionPrivate;
        this.questionTime = questionTime;
    }

    // Property accessors

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionClass() {
        return questionClass;
    }

    public void setQuestionClass(String questionClass) {
        this.questionClass = questionClass;
    }

    public String getQuestionContext() {
        return questionContext;
    }

    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionCreator() {
        return questionCreator;
    }

    public void setQuestionCreator(String questionCreator) {
        this.questionCreator = questionCreator;
    }

    public String getQuestionPrivate() {
        return questionPrivate;
    }

    public void setQuestionPrivate(String questionPrivate) {
        this.questionPrivate = questionPrivate;
    }

    public String getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }


}
