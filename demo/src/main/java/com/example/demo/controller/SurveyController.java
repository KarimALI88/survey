package com.example.demo.controller;

import com.example.demo.entity.SubmitAnswersRequestDTO;
import com.example.demo.entity.SubmitAnswersResponseDTO;
import com.example.demo.entity.SurveyDTO;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyDTO> getSurvey(@PathVariable Long surveyId,
                                               @RequestParam String language) {
        SurveyDTO surveyDTO = surveyService.getSurvey(surveyId, language);
        return ResponseEntity.ok(surveyDTO);
    }

    @PostMapping("/answer")
    public ResponseEntity<SubmitAnswersResponseDTO> submitAnswers(@RequestBody SubmitAnswersRequestDTO request) {
        SubmitAnswersResponseDTO response = surveyService.submitAnswers(request);
        return ResponseEntity.ok(response);
    }
}

