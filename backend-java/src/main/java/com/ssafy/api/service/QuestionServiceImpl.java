package com.ssafy.api.service;

import com.ssafy.db.entity.Question;
import com.ssafy.db.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    @Override
    public Question createQuestion(String topic, String questionContent, String level, String audioUrl) {
        Question question = new Question();
        question.setTopic(topic);
        question.setQuestionContent(questionContent);
        question.setLevel(level);
        question.setAudioUrl(audioUrl);

        questionRepository.save(question);
        return question;
    }
}
