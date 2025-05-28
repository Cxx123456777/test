package com.example.examsystem.mapper;

import com.example.examsystem.model.Answer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AnswerMapper {
    Answer findById(Long id);
    List<Answer> findByAnswerSheetId(Long answerSheetId);
    void insert(Answer answer);
    void insertBatch(List<Answer> answers);
    void update(Answer answer);
}
