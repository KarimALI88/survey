package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORM_QUT")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUST_ID")
    private Long questionId;

    @Column(name = "MajorQID", nullable = false)
    private Long majorQid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Survey survey;

    @Column(name = "QUST_DESC_EN", nullable = false)
    private String questionDescEn;

    @Column(name = "QUST_DESC_AR", nullable = false)
    private String questionDescAr;

    @Column(name = "TYPE_ID", nullable = false)
    private String typeId;

    @Column(name = "QUST_IS_MANDATORY", nullable = false)
    private String isMandatory;

    @Column(name = "QUST_IS_ACTIVE", nullable = false)
    private String isActive;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

}

