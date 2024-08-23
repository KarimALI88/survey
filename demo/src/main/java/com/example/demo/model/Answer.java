package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORM_QUT")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerID")
    private Long answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUST_ID", nullable = false)
    private Question question;

    @Column(name = "AnswerAR", nullable = false)
    private String answerAr;

    @Column(name = "AnswerEN", nullable = false)
    private String answerEn;

}

