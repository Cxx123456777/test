package com.example.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 试卷实体类
 * 
 * @author exam-system
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamPaper {
    /**
     * 试卷ID
     */
    private Long id;
    
    /**
     * 试卷标题
     */
    private String title;
    
    /**
     * 试卷描述
     */
    private String description;
    
    /**
     * 创建时间
     */
    private LocalDateTime creationDate;
    
    /**
     * 试卷包含的题目列表
     */
    private List<Question> questions;
}
