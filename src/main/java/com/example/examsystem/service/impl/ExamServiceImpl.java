package com.example.examsystem.service.impl;

import com.example.examsystem.mapper.ExamMapper;
import com.example.examsystem.mapper.ExamPaperMapper; // To validate ExamPaper exists
import com.example.examsystem.model.Exam;
import com.example.examsystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final ExamPaperMapper examPaperMapper; // To validate paper

    @Autowired
    public ExamServiceImpl(ExamMapper examMapper, ExamPaperMapper examPaperMapper) {
        this.examMapper = examMapper;
        this.examPaperMapper = examPaperMapper;
    }

    @Override
    @Transactional
    public Exam scheduleExam(Exam exam) {
        Objects.requireNonNull(exam, "Exam cannot be null");
        Objects.requireNonNull(exam.getExamPaperId(), "ExamPaper ID must be provided for an exam");
        // Validate that the exam paper exists
        if (examPaperMapper.findById(exam.getExamPaperId()) == null) {
            throw new RuntimeException("Cannot schedule exam: ExamPaper not found with id " + exam.getExamPaperId());
        }
        examMapper.insert(exam); // Assumes ID is populated
        // Handling examineeIds (List<String>) needs more thought.
        // If it's a join table, this is where you'd insert into exam_examinees.
        // For now, the Exam DTO holds them, but ExamMapper.xml doesn't directly persist this list.
        return exam;
    }

    @Override
    public Exam getExamById(Long id) {
        // ExamMapper.xml's findById currently returns a SimpleExamResultMap
        // which doesn't load ExamPaper details or examineeIds. This might be fine for some use cases.
        // If full details are needed, the ResultMap or query needs adjustment, or separate calls.
        return examMapper.findById(id);
    }

    @Override
    public List<Exam> getAllExams() {
        return examMapper.findAll();
    }

    @Override
    @Transactional
    public Exam updateExam(Long id, Exam examDetails) {
        Objects.requireNonNull(examDetails, "Exam details cannot be null");
        Exam existingExam = examMapper.findById(id);
        if (existingExam == null) {
            throw new RuntimeException("Exam not found with id: " + id);
        }
        if (examDetails.getExamPaperId() != null) {
            if (examPaperMapper.findById(examDetails.getExamPaperId()) == null) {
                throw new RuntimeException("Cannot update exam: ExamPaper not found with id " + examDetails.getExamPaperId());
            }
            existingExam.setExamPaperId(examDetails.getExamPaperId());
        }
        existingExam.setTitle(examDetails.getTitle());
        existingExam.setStartTime(examDetails.getStartTime());
        existingExam.setEndTime(examDetails.getEndTime());
        // Update examineeIds would require more complex logic (compare lists, update join table)
        examMapper.update(existingExam);
        return existingExam;
    }

    @Override
    @Transactional
    public void deleteExam(Long id) {
        // Also consider deleting from related tables like exam_examinees or answer_sheets if cascade isn't set up
        examMapper.delete(id);
    }
}
