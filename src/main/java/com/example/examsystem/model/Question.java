package com.example.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 题目实体类
 * 
 * @author exam-system
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    /**
     * 题目ID
     */
    private Long id;
    
    /**
     * 题目内容
     */
    private String content;
    
    /**
     * 题目类型
     */
    private QuestionType questionType;
    
    /**
     * 默认分值
     */
    private Double defaultScore;
    
    /**
     * 题目选项列表
     */
    private List<QuestionOption> options;
}
