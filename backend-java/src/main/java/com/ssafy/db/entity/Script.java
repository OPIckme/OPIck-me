package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
   스크립트 모델 정의
 */

@Entity
@Getter
@Setter
public class Script{

    @Id
    @Column(name = "script_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;



    @ManyToOne(targetEntity = Question.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    Question question;


    String scriptContent;
    String createdAt;
    String audioUrl;
}
