package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
   스크립트 모델 정의
 */

@Entity
@Getter
@Setter
public class Script{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(targetEntity = Question.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    Question question;

    @JsonIgnore
    @OneToMany(mappedBy = "script")
    private List<Feedback> feedbacks = new ArrayList<>();

    @JsonProperty("feedbacks")
    public boolean getFeedbacks() {
        return feedbacks.isEmpty();
    }

    @Lob
    String scriptContent;

    @CreationTimestamp
    Timestamp createdAt;

    String audioUrl;
}
