package com.example.examsystem.service;

import com.example.examsystem.model.Answer; // For submitting answers
import com.example.examsystem.model.AnswerSheet;
import java.util.List;

public interface AnswerSheetService {
    AnswerSheet createAnswerSheet(AnswerSheet answerSheet); // For when an examinee starts an exam
    AnswerSheet getAnswerSheetById(Long id);
    List<AnswerSheet> getAnswerSheetsByExamId(Long examId);
    List<AnswerSheet> getAnswerSheetsByExamineeId(String examineeId);
    AnswerSheet recordAnswer(Long answerSheetId, Answer answer); // Record a single answer
    AnswerSheet submitAnswers(Long answerSheetId, List<Answer> answers); // Submit all answers
    AnswerSheet gradeAnswerSheet(Long answerSheetId); // Logic to calculate score
}
