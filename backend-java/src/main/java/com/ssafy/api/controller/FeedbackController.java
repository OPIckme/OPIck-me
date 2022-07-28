package com.ssafy.api.controller;

import com.ssafy.api.request.FeedbackRegisterPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.FeedbackService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Feedback;
import com.ssafy.db.entity.Script;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
@Api(value = "피드백 API", tags = {"Feedback."})
@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final ScriptService scriptService;

    @PostMapping("/")
    @ApiOperation(value = "피드백 생성", notes = "원본 스크립트 id와 피드백 내용으로 피드백을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> create(@RequestBody @ApiParam(value="피드백생성 정보", required = true) @Validated FeedbackRegisterPostReq feedbackRegisterPostReq){
        Long scriptId = feedbackRegisterPostReq.getScriptId();
        String content = feedbackRegisterPostReq.getContent();

        Optional<Script> script = scriptService.getScriptByScriptId(scriptId);
        boolean isOverlap = feedbackService.overlapCheck(scriptId);
        if (isOverlap)
            return ResponseEntity.status(500).body(BaseResponseBody.of(500,"이미 피드백이 진행된 스크립트입니다."));
        if (script.isPresent()){
            feedbackService.create(content,script.get());
            return ResponseEntity.status(201).body(BaseResponseBody.of(201,"피드백 추가 성공"));
        }
        return ResponseEntity.status(404).body(BaseResponseBody.of(404,"존재하지 않는 스크립트 입니다."));
    }

    @GetMapping("/")
    @ApiOperation(value = "전체 피드백 조회", notes = "모든 피드백을 조회한다.")

    public List<Feedback> getList(){
        return feedbackService.getList();
    }

    @GetMapping("/{feedbackId}")
    @ApiOperation(value = "피드백 디테일 조회", notes = "피드백 id로 디테일 조회.")
    public Feedback getDetail(@PathVariable Long feedbackId){
        return feedbackService.getDetail(feedbackId).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 피드백이 없습니다. id : " + feedbackId);
            }
        });
    }

    @DeleteMapping("/{feedbackId}")
    @ApiOperation(value = "피드백 삭제", notes = "피드백 한개 삭제.")
    public ResponseEntity<BaseResponseBody> delete(@PathVariable Long feedbackId){
        BaseResponseBody responseBody = feedbackService.delete(feedbackId);
        Integer statusCode = responseBody.getStatusCode();
        return ResponseEntity.status(statusCode).body(responseBody);
    }
}
