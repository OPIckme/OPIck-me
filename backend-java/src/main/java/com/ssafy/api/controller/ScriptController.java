package com.ssafy.api.controller;


import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.api.response.ScriptDetailRes;
import com.ssafy.api.response.ScriptListRes;
import com.ssafy.api.service.QuestionService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Api(value = "스크립트 API", tags = {"Script."})
@RestController
@RequestMapping("/api/v1/script")
@RequiredArgsConstructor
public class ScriptController {
    private final ScriptService scriptService;
    private final UserService userService;
    private final QuestionService questionService;

    @PostMapping
    @ApiOperation(value = "스크립트 등록", notes = "userId,questionId,content,audiourl을 받아서 스크립트를 등록한다.")
    public ResponseEntity createScript(@RequestBody ScriptRegisterPostReq scriptRegisterPostReq){
        Long userId = scriptRegisterPostReq.getUserId();
        Long questionId = scriptRegisterPostReq.getQuestionId();

        //userService에 구현 필요
        Optional<User> user = userService.getUserByUserId(userId);
        Optional<Question> question = questionService.getQuestionByQuestionId(questionId);
        String content = scriptRegisterPostReq.getScriptContent();
        String audioUrl = scriptRegisterPostReq.getAudioURL();
        scriptService.createScript(user.get(), question.get(),content,audioUrl);



        return ResponseEntity.status(201).body(BaseResponseBody.of(201,"스크립트 추가 성공"));
    }

    @GetMapping
    public ResponseEntity<ScriptListRes> getList(){
        List<Script> scriptList = scriptService.getScriptList();

        return ResponseEntity.status(200).body(ScriptListRes.of(200,"스크립트 리스트 불러오기 성공!",scriptList));
    }

    @GetMapping("/{scriptId}")
    public ResponseEntity<ScriptDetailRes> getScriptDetail(@PathVariable Long scriptId){
        Script script = scriptService.getDetail(scriptId).get();

        return ResponseEntity.status(200).body(ScriptDetailRes.of(200,"스크립트 세부사항 불러오기 성공!",script));
    }

    @DeleteMapping("/{scriptId}")
    public ResponseEntity<BaseResponseBody> delete(@PathVariable Long scriptId){
        scriptService.deleteByScriptId(scriptId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"스크립트 삭제 성공!!"));
    }
}
