package com.example.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目选项实体类
 * 
 * @author exam-system
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOption {
    /**
     * 选项ID
     */
    private Long id;
    
    /**
     * 选项文本内容
     */
    private String optionText;
    
    /**
     * 是否为正确答案
     */
    private boolean isCorrect;
    
    /**
     * 所属题目ID
     */
    private Long questionId;
}
