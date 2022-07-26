package com.ssafy.api.service;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Feedback;
import com.ssafy.db.entity.Script;
import com.ssafy.db.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Transactional
    public void create(String content, Script script){
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setScript(script);
        feedbackRepository.save(feedback);
    }

    public BaseResponseBody delete(Long feedbackId){
        try{
            feedbackRepository.deleteById(feedbackId);
        }catch (IllegalArgumentException e){
            return BaseResponseBody.of(500,"삭제 실패");
        }
        return BaseResponseBody.of(200,"삭제 성공");
    }

    @Transactional(readOnly = true)
    public List<Feedback> getList(){
        return feedbackRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Feedback> getDetail(Long feedbackId){
        return feedbackRepository.findById(feedbackId);
    }

    @Transactional(readOnly = true)
    public boolean overlapCheck(Long scriptId){
        return feedbackRepository.findByScriptId(scriptId).isPresent();
    }
}