package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORM_UserAnswer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Survey survey;

    @Column(name = "MajorQID", nullable = false)
    private Long majorQid;

    @Column(name = "IDNumber", nullable = false)
    private Long idNumber;

    @Column(name = "AnswerAR", nullable = false)
    private String answerAr;

    @Column(name = "AnswerEN", nullable = false)
    private String answerEn;

    @Column(name = "Channel", nullable = false)
    private String channel;

    @Column(name = "MobileNo", nullable = false)
    private Long mobileNo;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Note")
    private String note;

}
