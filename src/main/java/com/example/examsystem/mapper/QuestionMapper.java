package com.example.examsystem.mapper;

import com.example.examsystem.model.Question;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 题目数据访问层接口
 * 
 * @author exam-system
 * @version 1.0.0
 */
@Mapper
public interface QuestionMapper {
    
    /**
     * 根据ID查找题目
     * 
     * @param id 题目ID
     * @return 题目信息
     */
    Question findById(Long id);
    
    /**
     * 查找所有题目
     * 
     * @return 题目列表
     */
    List<Question> findAll();
    
    /**
     * 插入新题目
     * 
     * @param question 题目信息
     */
    void insert(Question question);
    
    /**
     * 更新题目信息
     * 
     * @param question 题目信息
     */
    void update(Question question);
    
    /**
     * 删除题目
     * 
     * @param id 题目ID
     */
    void delete(Long id);
}
