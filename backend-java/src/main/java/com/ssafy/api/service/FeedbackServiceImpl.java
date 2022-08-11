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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public Feedback create(String content, Script script){
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setScript(script);
        return feedbackRepository.save(feedback);
    }

    @Override
    public BaseResponseBody delete(Long feedbackId){
        feedbackRepository.deleteById(feedbackId);
        return BaseResponseBody.of(200,"삭제 성공");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Feedback> getList(Long userId){
        return feedbackRepository.findAll().stream().filter(v -> v.getScript().getUser().getId() == userId).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Feedback> getDetail(Long feedbackId, Long userId) {
        return feedbackRepository.findById(feedbackId).filter(v->v.getScript().getUser().getId() == userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean overlapCheck(Long scriptId){
        return feedbackRepository.findByScriptId(scriptId).isPresent();
    }
}