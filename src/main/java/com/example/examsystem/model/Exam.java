package com.example.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private Long id;
    private String title;
    private Long examPaperId; // Using direct ID as per current ExamServiceImpl prompt
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> examineeIds;
}
