package com.example.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List; // For MULTIPLE_CHOICE selected IDs, if not using String

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private Long id;
    private Long answerSheetId; // Direct ID
    private Long questionId;

    // Fields based on AnswerMapper.xml and AnswerSheetServiceImpl grading logic
    private Long selectedOptionId;    // For SINGLE_CHOICE, TRUE_FALSE
    private String selectedOptionIds; // For MULTIPLE_CHOICE (e.g., comma-separated string of IDs)
    private String answerText;        // For FILL_IN_THE_BLANK

    private boolean isCorrect;
    private double score;
}
