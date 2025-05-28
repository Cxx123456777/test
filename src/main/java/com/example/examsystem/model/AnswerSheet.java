package com.example.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSheet {
    private Long id;
    private Long examId; // Direct ID
    private String examineeId;
    private LocalDateTime submissionTime;
    private List<Answer> answers;
    private double score; // Score for the sheet
}
