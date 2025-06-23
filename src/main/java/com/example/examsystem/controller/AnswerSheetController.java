package com.example.examsystem.controller;

import com.example.examsystem.model.Answer;
import com.example.examsystem.model.AnswerSheet;
import com.example.examsystem.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 答题卡管理控制器
 * 
 * @author exam-system
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/answer-sheets")
public class AnswerSheetController {

    private final AnswerSheetService answerSheetService;

    @Autowired
    public AnswerSheetController(AnswerSheetService answerSheetService) {
        this.answerSheetService = answerSheetService;
    }

    /**
     * 创建答题卡
     */
    @PostMapping
    public ResponseEntity<AnswerSheet> createAnswerSheet(@RequestBody AnswerSheet answerSheet) {
        try {
            AnswerSheet createdSheet = answerSheetService.createAnswerSheet(answerSheet);
            return new ResponseEntity<>(createdSheet, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 根据ID获取答题卡
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnswerSheet> getAnswerSheetById(@PathVariable Long id) {
        AnswerSheet answerSheet = answerSheetService.getAnswerSheetById(id);
        if (answerSheet != null) {
            return ResponseEntity.ok(answerSheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 根据考试ID获取答题卡列表
     */
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<AnswerSheet>> getAnswerSheetsByExamId(@PathVariable Long examId) {
        List<AnswerSheet> answerSheets = answerSheetService.getAnswerSheetsByExamId(examId);
        return ResponseEntity.ok(answerSheets);
    }

    /**
     * 根据考生ID获取答题卡列表
     */
    @GetMapping("/examinee/{examineeId}")
    public ResponseEntity<List<AnswerSheet>> getAnswerSheetsByExamineeId(@PathVariable String examineeId) {
        List<AnswerSheet> answerSheets = answerSheetService.getAnswerSheetsByExamineeId(examineeId);
        return ResponseEntity.ok(answerSheets);
    }

    /**
     * 记录单个答案
     */
    @PostMapping("/{answerSheetId}/answers")
    public ResponseEntity<AnswerSheet> recordAnswer(@PathVariable Long answerSheetId, @RequestBody Answer answer) {
        try {
            AnswerSheet updatedSheet = answerSheetService.recordAnswer(answerSheetId, answer);
            return ResponseEntity.ok(updatedSheet);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 提交答案
     */
    @PostMapping("/{answerSheetId}/submit")
    public ResponseEntity<AnswerSheet> submitAnswers(@PathVariable Long answerSheetId, @RequestBody List<Answer> answers) {
        try {
            AnswerSheet submittedSheet = answerSheetService.submitAnswers(answerSheetId, answers);
            return ResponseEntity.ok(submittedSheet);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 评分答题卡
     */
    @PostMapping("/{answerSheetId}/grade")
    public ResponseEntity<AnswerSheet> gradeAnswerSheet(@PathVariable Long answerSheetId) {
        try {
            AnswerSheet gradedSheet = answerSheetService.gradeAnswerSheet(answerSheetId);
            return ResponseEntity.ok(gradedSheet);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
