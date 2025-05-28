package com.example.examsystem.service;

import com.example.examsystem.model.Question;
import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
    Question updateQuestion(Long id, Question questionDetails);
    void deleteQuestion(Long id);
    // Consider adding methods for importing questions or finding by type later
}
