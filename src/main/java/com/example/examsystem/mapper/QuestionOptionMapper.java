package com.example.examsystem.mapper;

import com.example.examsystem.model.QuestionOption;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 题目选项数据访问层接口
 * 
 * @author exam-system
 * @version 1.0.0
 */
@Mapper
public interface QuestionOptionMapper {
    
    /**
     * 根据ID查找选项
     * 
     * @param id 选项ID
     * @return 选项信息
     */
    QuestionOption findById(Long id);
    
    /**
     * 根据题目ID查找选项列表
     * 
     * @param questionId 题目ID
     * @return 选项列表
     */
    List<QuestionOption> findByQuestionId(Long questionId);
    
    /**
     * 插入新选项
     * 
     * @param option 选项信息
     */
    void insert(QuestionOption option);
    
    /**
     * 批量插入选项
     * 
     * @param options 选项列表
     */
    void insertBatch(List<QuestionOption> options);
    
    /**
     * 更新选项信息
     * 
     * @param option 选项信息
     */
    void update(QuestionOption option);
    
    /**
     * 删除选项
     * 
     * @param id 选项ID
     */
    void delete(Long id);
    
    /**
     * 根据题目ID删除所有选项
     * 
     * @param questionId 题目ID
     */
    void deleteByQuestionId(Long questionId);
}
