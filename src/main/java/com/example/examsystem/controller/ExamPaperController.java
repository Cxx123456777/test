package com.example.examsystem.controller;

import com.example.examsystem.model.ExamPaper;
import com.example.examsystem.model.Question; // For response type
import com.example.examsystem.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exampapers")
public class ExamPaperController {

    private final ExamPaperService examPaperService;

    @Autowired
    public ExamPaperController(ExamPaperService examPaperService) {
        this.examPaperService = examPaperService;
    }

    @PostMapping
    public ResponseEntity<ExamPaper> createExamPaper(@RequestBody ExamPaper examPaper) {
        ExamPaper createdExamPaper = examPaperService.createExamPaper(examPaper);
        return new ResponseEntity<>(createdExamPaper, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamPaper> getExamPaperById(@PathVariable Long id) {
        ExamPaper examPaper = examPaperService.getExamPaperById(id);
        if (examPaper != null) {
            return ResponseEntity.ok(examPaper);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ExamPaper>> getAllExamPapers() {
        List<ExamPaper> examPapers = examPaperService.getAllExamPapers();
        return ResponseEntity.ok(examPapers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamPaper> updateExamPaper(@PathVariable Long id, @RequestBody ExamPaper examPaperDetails) {
        try {
            ExamPaper updatedExamPaper = examPaperService.updateExamPaper(id, examPaperDetails);
            return ResponseEntity.ok(updatedExamPaper);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamPaper(@PathVariable Long id) {
        try {
            examPaperService.deleteExamPaper(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{examPaperId}/questions/{questionId}")
    public ResponseEntity<Void> addQuestionToExamPaper(@PathVariable Long examPaperId, @PathVariable Long questionId) {
        try {
            examPaperService.addQuestionToExamPaper(examPaperId, questionId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) { // Catch if paper or question not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{examPaperId}/questions/{questionId}")
    public ResponseEntity<Void> removeQuestionFromExamPaper(@PathVariable Long examPaperId, @PathVariable Long questionId) {
        try {
            examPaperService.removeQuestionFromExamPaper(examPaperId, questionId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Handle specific exceptions if service throws them for not found etc.
            return ResponseEntity.badRequest().build(); // Or notFound
        }
    }

    @GetMapping("/{examPaperId}/questions")
    public ResponseEntity<List<Question>> getQuestionsForExamPaper(@PathVariable Long examPaperId) {
        try {
            List<Question> questions = examPaperService.getQuestionsForExamPaper(examPaperId);
            return ResponseEntity.ok(questions);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
