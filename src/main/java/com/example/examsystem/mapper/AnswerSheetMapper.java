package com.example.examsystem.mapper;

import com.example.examsystem.model.AnswerSheet;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AnswerSheetMapper {
    AnswerSheet findById(Long id);
    List<AnswerSheet> findByExamId(Long examId);
    List<AnswerSheet> findByExamineeId(String examineeId);
    void insert(AnswerSheet answerSheet);
    void update(AnswerSheet answerSheet);
}
