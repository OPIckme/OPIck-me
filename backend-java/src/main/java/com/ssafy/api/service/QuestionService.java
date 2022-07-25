package com.ssafy.api.service;

import com.ssafy.db.entity.Question;

public interface QuestionService {
    Question createQuestion(String topic, String questionContent, String level, String audioUrl);

}
