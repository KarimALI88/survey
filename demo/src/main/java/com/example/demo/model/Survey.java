package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORM_SURVEY")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SURVEY_ID")
    private Long surveyId;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Active", nullable = false)
    private String active;

    @Column(name = "Effective", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "Expiry", nullable = false)
    private LocalDate expiryDate;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAnswer> userAnswers = new ArrayList<>();

}
