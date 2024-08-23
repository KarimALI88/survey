package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAnswersResponseDTO {
    private String status; // "Successful" or "Error"
    private List<String> errors = new ArrayList<>(); // List of error messages, if any
}
