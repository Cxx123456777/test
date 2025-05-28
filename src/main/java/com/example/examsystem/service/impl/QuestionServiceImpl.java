package com.example.examsystem.service.impl;

import com.example.examsystem.mapper.QuestionMapper;
import com.example.examsystem.mapper.QuestionOptionMapper;
import com.example.examsystem.model.Question;
import com.example.examsystem.model.QuestionOption;
import com.example.examsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Transactional

import java.util.List;
import java.util.Objects; // For Objects.requireNonNull

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionOptionMapper questionOptionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, QuestionOptionMapper questionOptionMapper) {
        this.questionMapper = questionMapper;
        this.questionOptionMapper = questionOptionMapper;
    }

    @Override
    @Transactional // Ensure atomicity
    public Question createQuestion(Question question) {
        Objects.requireNonNull(question, "Question cannot be null");
        questionMapper.insert(question); // Assumes ID is populated by MyBatis useGeneratedKeys
        if (question.getOptions() != null && !question.getOptions().isEmpty()) {
            for (QuestionOption option : question.getOptions()) {
                option.setQuestionId(question.getId());
            }
            questionOptionMapper.insertBatch(question.getOptions());
        }
        return question; // Question object now contains the ID and potentially populated options
    }

    @Override
    public Question getQuestionById(Long id) {
        // The QuestionResultMap in QuestionMapper.xml should load options
        return questionMapper.findById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        // The QuestionResultMap should load options for each question
        return questionMapper.findAll();
    }

    @Override
    @Transactional
    public Question updateQuestion(Long id, Question questionDetails) {
        Objects.requireNonNull(questionDetails, "Question details cannot be null");
        Question existingQuestion = questionMapper.findById(id);
        if (existingQuestion == null) {
            // Or throw custom exception e.g., ResourceNotFoundException
            throw new RuntimeException("Question not found with id: " + id);
        }
        existingQuestion.setContent(questionDetails.getContent());
        existingQuestion.setQuestionType(questionDetails.getQuestionType());
        existingQuestion.setDefaultScore(questionDetails.getDefaultScore());
        questionMapper.update(existingQuestion);

        // Handle options update: delete old, insert new (common strategy)
        if (questionDetails.getOptions() != null) {
            questionOptionMapper.deleteByQuestionId(id);
            if (!questionDetails.getOptions().isEmpty()) {
                for (QuestionOption option : questionDetails.getOptions()) {
                    option.setQuestionId(id);
                }
                questionOptionMapper.insertBatch(questionDetails.getOptions());
                existingQuestion.setOptions(questionDetails.getOptions()); // Update in-memory object
            } else {
                 existingQuestion.setOptions(null); // Clear options if new list is empty
            }
        }
        return existingQuestion;
    }

    @Override
    @Transactional
    public void deleteQuestion(Long id) {
        // Options should be deleted by cascade in DB or explicitly here if not set.
        // The XML for QuestionOptionMapper.deleteByQuestionId handles options.
        // Or, if foreign key with cascade delete is set up in DB for question_option.question_id -> question.id
        questionOptionMapper.deleteByQuestionId(id); // Ensure options are deleted first
        questionMapper.delete(id);
    }
}
