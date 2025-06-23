package com.example.examsystem.mapper;

import com.example.examsystem.model.ExamPaper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 试卷数据访问层接口
 * 
 * @author exam-system
 * @version 1.0.0
 */
@Mapper
public interface ExamPaperMapper {
    
    /**
     * 根据ID查找试卷
     * 
     * @param id 试卷ID
     * @return 试卷信息
     */
    ExamPaper findById(Long id);
    
    /**
     * 查找所有试卷
     * 
     * @return 试卷列表
     */
    List<ExamPaper> findAll();
    
    /**
     * 插入新试卷
     * 
     * @param examPaper 试卷信息
     */
    void insert(ExamPaper examPaper);
    
    /**
     * 更新试卷信息
     * 
     * @param examPaper 试卷信息
     */
    void update(ExamPaper examPaper);
    
    /**
     * 删除试卷
     * 
     * @param id 试卷ID
     */
    void delete(Long id);
    
    /**
     * 向试卷添加题目
     * 
     * @param examPaperId 试卷ID
     * @param questionId 题目ID
     */
    void addQuestionToPaper(Long examPaperId, Long questionId);
    
    /**
     * 从试卷移除题目
     * 
     * @param examPaperId 试卷ID
     * @param questionId 题目ID
     */
    void removeQuestionFromPaper(Long examPaperId, Long questionId);
    
    /**
     * 获取试卷的题目ID列表
     * 
     * @param examPaperId 试卷ID
     * @return 题目ID列表
     */
    List<Long> findQuestionIdsByPaperId(Long examPaperId);
}
