package com.ssafy.api.service;

import com.ssafy.db.entity.Question;

import java.util.Optional;

public interface QuestionService {
    Question createQuestion(String topic, String questionContent, String level, String audioUrl);

    Optional<Question> getQuestionByQuestionId(Long questionId);
}
