package com.ssafy.api.controller;


import com.ssafy.api.request.FeedbackRegisterPostReq;
import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.api.service.ScriptService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Script;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(value = "스크립트 API", tags = {"Script."})
@RestController
@RequestMapping("/api/v1/script")
@RequiredArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;
    @PostMapping
    public ResponseEntity<BaseResponseBody> create(ScriptRegisterPostReq scriptRegisterPostReq){
        scriptService.createScript(scriptRegisterPostReq);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201,"스크립트 생성 성공"));
    }
}
