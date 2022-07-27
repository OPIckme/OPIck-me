package com.ssafy.api.controller;


import com.ssafy.api.request.QuestionRandomGetReq;
import com.ssafy.api.response.QuestionRes;
import com.ssafy.api.service.QuestionService;
import com.ssafy.db.entity.Question;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/random")
    public ResponseEntity<QuestionRes> randomQuestion(@RequestBody QuestionRandomGetReq questionRandomGetReq) {
        Question question = questionService.getRandomDetail(questionRandomGetReq.getTopic()).get();
        QuestionRes questionRes = new QuestionRes();

        questionRes.setStatusCode(200);
        questionRes.setMessage("Success");
        questionRes.setId(question.getId());
        questionRes.setTopic(question.getTopic());
        questionRes.setQuestionContent(questionRes.getQuestionContent());
        questionRes.setLevel(question.getLevel());
        questionRes.setAudioUrl(question.getAudioUrl());

        return ResponseEntity.ok(questionRes);
    }

}
