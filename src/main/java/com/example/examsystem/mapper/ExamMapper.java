package com.example.examsystem.mapper;

import com.example.examsystem.model.Exam;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ExamMapper {
    Exam findById(Long id);
    List<Exam> findAll();
    void insert(Exam exam);
    void update(Exam exam);
    void delete(Long id);
}
