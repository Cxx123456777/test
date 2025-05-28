package com.example.examsystem.service.impl;

import com.example.examsystem.mapper.ExamPaperMapper;
import com.example.examsystem.mapper.QuestionMapper; // To fetch full Question objects
import com.example.examsystem.model.ExamPaper;
import com.example.examsystem.model.Question;
import com.example.examsystem.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    private final ExamPaperMapper examPaperMapper;
    private final QuestionMapper questionMapper; // To fetch full Question objects

    @Autowired
    public ExamPaperServiceImpl(ExamPaperMapper examPaperMapper, QuestionMapper questionMapper) {
        this.examPaperMapper = examPaperMapper;
        this.questionMapper = questionMapper;
    }

    @Override
    @Transactional
    public ExamPaper createExamPaper(ExamPaper examPaper) {
        Objects.requireNonNull(examPaper, "ExamPaper cannot be null");
        // If creationDate is not set in DTO, it's handled by NOW() in XML or DB default
        examPaperMapper.insert(examPaper); // Assumes ID is populated
        // Handle linking questions if IDs are provided in examPaper.getQuestions() (simplified here)
        // Typically, questions are added via addQuestionToExamPaper after paper creation
        return examPaper;
    }

    @Override
    public ExamPaper getExamPaperById(Long id) {
        // ExamPaperResultMap in XML should load associated questions
        return examPaperMapper.findById(id);
    }

    @Override
    public List<ExamPaper> getAllExamPapers() {
        // ExamPaperResultMap should load questions for each paper (can be heavy)
        // Consider a lighter query for list views if performance is an issue
        return examPaperMapper.findAll();
    }

    @Override
    @Transactional
    public ExamPaper updateExamPaper(Long id, ExamPaper examPaperDetails) {
        Objects.requireNonNull(examPaperDetails, "ExamPaper details cannot be null");
        ExamPaper existingExamPaper = examPaperMapper.findById(id);
        if (existingExamPaper == null) {
            throw new RuntimeException("ExamPaper not found with id: " + id);
        }
        existingExamPaper.setTitle(examPaperDetails.getTitle());
        existingExamPaper.setDescription(examPaperDetails.getDescription());
        // Questions list update would typically be handled by add/remove methods
        examPaperMapper.update(existingExamPaper);
        return existingExamPaper;
    }

    @Override
    @Transactional
    public void deleteExamPaper(Long id) {
        // The ExamPaperMapper.xml's delete operation also deletes from exampaper_questions
        examPaperMapper.delete(id);
    }

    @Override
    @Transactional
    public void addQuestionToExamPaper(Long examPaperId, Long questionId) {
        // Basic validation (e.g., check if paper and question exist) could be added
        examPaperMapper.addQuestionToPaper(examPaperId, questionId);
    }

    @Override
    @Transactional
    public void removeQuestionFromExamPaper(Long examPaperId, Long questionId) {
        examPaperMapper.removeQuestionFromPaper(examPaperId, questionId);
    }

    @Override
    public List<Question> getQuestionsForExamPaper(Long examPaperId) {
        ExamPaper paper = examPaperMapper.findById(examPaperId); // Corrected to use examPaperId
        return paper != null ? paper.getQuestions() : new ArrayList<>();
        // Alternative: if findById doesn't load full questions or only loads IDs:
        // List<Long> questionIds = examPaperMapper.findQuestionIdsByPaperId(examPaperId);
        // List<Question> questions = new ArrayList<>();
        // if (questionIds != null && !questionIds.isEmpty()) {
        //     for (Long qId : questionIds) {
        //         Question q = questionMapper.findById(qId); // Assumes this loads options too
        //         if (q != null) questions.add(q);
        //     }
        // }
        // return questions;
    }
}
