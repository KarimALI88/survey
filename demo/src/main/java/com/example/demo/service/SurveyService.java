package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.SurveyExpiredException;
import com.example.demo.exceptions.SurveyNotFoundException;
import com.example.demo.model.Question;
import com.example.demo.model.Survey;
import com.example.demo.model.UserAnswer;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;

//    public SurveyDTO getSurvey(Long surveyId, String language) {
//        Survey survey = surveyRepository.findById(surveyId)
//                .orElseThrow(() -> new SurveyNotFoundException("Survey not found"));
//
//        if ("No".equalsIgnoreCase(survey.getActive())) {
//            throw new SurveyExpiredException("Survey is not active");
//        }
//
//        List<Question> questions = questionRepository.findBySurveyId(surveyId);
//
//        // Transform to DTO
//        SurveyDTO surveyDTO = new SurveyDTO();
//        surveyDTO.setSurveyId(survey.getSurveyId());
//        surveyDTO.setDescription(survey.getDescription());
//
//        for (Question question : questions) {
//            QuestionDTO questionDTO = new QuestionDTO();
//            questionDTO.setQuestionId(question.getQuestionId());
//            questionDTO.setQuestionText("EN".equalsIgnoreCase(language) ?
//                    question.getQuestionDescEn() : question.getQuestionDescAr());
//            questionDTO.setMandatory("Yes".equalsIgnoreCase(question.getIsMandatory()));
//
//            surveyDTO.getQuestions().add(questionDTO);
//        }
//
//        return surveyDTO;
//    }
    public SurveyDTO getSurvey(Long surveyId, String language) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException("Survey not found"));

        if ("No".equalsIgnoreCase(survey.getActive())) {
            throw new SurveyExpiredException("Survey is not active");
        }

        List<Question> questions = questionRepository.findAll();

        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setSurveyId(survey.getSurveyId());
        surveyDTO.setSurveyName(survey.getDescription()); // Set survey name
        surveyDTO.setDescription(survey.getDescription());

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(question.getQuestionId());
            questionDTO.setQuestionText("EN".equalsIgnoreCase(language) ?
                    question.getQuestionDescEn() : question.getQuestionDescAr());
            questionDTO.setQuestionType(question.getTypeId()); // Set question type
            questionDTO.setMandatory("Y".equalsIgnoreCase(question.getIsMandatory()));

            // Add acceptable answers
            List<AnswerDTO> answerDTOs = userAnswerRepository.findById(question.getQuestionId()).stream()
                    .map(answer -> new AnswerDTO(answer.getId(),
                            "EN".equalsIgnoreCase(language) ? answer.getAnswerEn() : answer.getAnswerAr()))
                    .collect(Collectors.toList());

            questionDTO.setAcceptableAnswers(answerDTOs);

            surveyDTO.getQuestions().add(questionDTO);
        }

        return surveyDTO;
    }

    public SubmitAnswersResponseDTO submitAnswers(SubmitAnswersRequestDTO request) {
        SubmitAnswersResponseDTO response = new SubmitAnswersResponseDTO();
        List<String> errors = new ArrayList<>();

        for (UserAnswerDTO answerDTO : request.getAnswers()) {
            if (isMandatoryQuestionNotAnswered(answerDTO)) {
                errors.add("Mandatory question not answered for question ID: " + answerDTO.getQuestionId());
            }

            if (!isQuestionIdCorrect(answerDTO.getQuestionId())) {
                errors.add("Question ID not correct: " + answerDTO.getQuestionId());
            }
        }

        if (errors.isEmpty()) {
            // Save the answers to the database
            for (UserAnswerDTO answerDTO : request.getAnswers()) {
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.getSurvey().getQuestions().add(Question.builder().questionId(answerDTO.getQuestionId()).build());
                userAnswer.setAnswerEn(answerDTO.getAnswerText());
                userAnswer.setChannel(request.getChannel());
                // Set other fields as necessary
                userAnswerRepository.save(userAnswer);
            }
            response.setStatus("Successful");
        } else {
            response.setStatus("Error");
            response.setErrors(errors);
        }

        return response;
    }

    private boolean isMandatoryQuestionNotAnswered(UserAnswerDTO answerDTO) {
        // Logic to check if the mandatory question was answered
        return answerDTO.getAnswerText() == null || answerDTO.getAnswerText().isEmpty();
    }

    private boolean isQuestionIdCorrect(Long questionId) {
        // Logic to validate question ID
        return questionRepository.existsById(questionId);
    }

}

