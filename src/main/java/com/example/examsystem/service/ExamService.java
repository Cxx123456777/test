package com.example.examsystem.service;

import com.example.examsystem.model.Exam;
import com.example.examsystem.model.AnswerSheet; // For submitExam
import java.util.List;
import java.util.Map; // For answers in submitExam

public interface ExamService {
    Exam scheduleExam(Exam exam);
    Exam getExamById(Long id);
    List<Exam> getAllExams();
    Exam updateExam(Long id, Exam examDetails);
    void deleteExam(Long id);
    // Methods for candidate experience
    // AnswerSheet startExam(Long examId, String examineeId); // Might create an AnswerSheet
    // AnswerSheet submitExam(Long answerSheetId, Map<Long, List<String>> answers); // Key: QuestionId, Value: SelectedOptionIds or AnswerText
}
