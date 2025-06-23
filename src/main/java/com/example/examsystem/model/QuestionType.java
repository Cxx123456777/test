package com.example.examsystem.model;

/**
 * 题目类型枚举
 * 
 * @author exam-system
 * @version 1.0.0
 */
public enum QuestionType {
    /**
     * 单选题
     */
    SINGLE_CHOICE("单选题"),
    
    /**
     * 多选题
     */
    MULTIPLE_CHOICE("多选题"),
    
    /**
     * 判断题
     */
    TRUE_FALSE("判断题"),
    
    /**
     * 填空题
     */
    FILL_IN_THE_BLANK("填空题");

    private final String description;

    QuestionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据描述获取题目类型
     * 
     * @param description 描述
     * @return 题目类型
     */
    public static QuestionType fromDescription(String description) {
        for (QuestionType type : QuestionType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的题目类型: " + description);
    }
}
