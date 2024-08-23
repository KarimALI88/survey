package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String questionText;
    private String questionType;
    private boolean isMandatory;
    private List<AnswerDTO> acceptableAnswers = new ArrayList<>();
}
