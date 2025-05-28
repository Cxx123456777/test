package com.example.examsystem.service;

import com.example.examsystem.model.ExamPaper;
import com.example.examsystem.model.Question; // For return type
import java.util.List;

public interface ExamPaperService {
    ExamPaper createExamPaper(ExamPaper examPaper);
    ExamPaper getExamPaperById(Long id);
    List<ExamPaper> getAllExamPapers();
    ExamPaper updateExamPaper(Long id, ExamPaper examPaperDetails);
    void deleteExamPaper(Long id);
    void addQuestionToExamPaper(Long examPaperId, Long questionId);
    void removeQuestionFromExamPaper(Long examPaperId, Long questionId);
    List<Question> getQuestionsForExamPaper(Long examPaperId); // To get fully populated Question objects
}
