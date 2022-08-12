package com.ssafy.api.controller;

import com.ssafy.api.request.ConsultRegisterPostReq;
import com.ssafy.api.response.ConsultMapRes;
import com.ssafy.api.response.ConsultRes;
import com.ssafy.api.service.ConsultService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Api(value = "상담 API", tags = {"Consult."})
@RestController
@RequestMapping("/api/v1/consult")
@RequiredArgsConstructor
public class ConsultController {
    private final ConsultService consultService;
    private final ScriptService scriptService;

    @PostMapping
    @ApiOperation(value = "상담 신청", notes = "<strong>스크립트</strong>를 가지고 상담신청을 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "스크립트 없음")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="상담신청 정보", required = true) @Validated ConsultRegisterPostReq consultRegisterPostReq) {
        Long scriptId = consultRegisterPostReq.getScriptId();
        Optional<Script> script = scriptService.getScriptByScriptId(scriptId);
        String room = consultRegisterPostReq.getRoom();


        Consult consult=consultService.create(script.get(), room);
        return ResponseEntity.status(201).body(ConsultRes.of(201, "Success created consult",consult));
        }

    @GetMapping
    @ApiOperation(value = "대기중인 상담 조회", notes = "대기중인 상담 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "대기중인 상담 목록 조회 성공", response = ConsultMapRes.class),
    })
    public ResponseEntity<ConsultMapRes> getWaitingMap() {
        Map<Long, Consult> waitingConsultMap = consultService.waitingConsultMap();
        return ResponseEntity.status(200).body(ConsultMapRes.of(200,"Success to get consult list", waitingConsultMap));
    }

    @DeleteMapping("/complete/{consultId}")
    @ApiOperation(value = "상담 완료 삭제", notes = "상담 완료로 db에서 삭제.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "상담 없음"),
            @ApiResponse(code = 400, message = "이미 변경된 상태")
    })
    public ResponseEntity<? extends BaseResponseBody> complete(@PathVariable Long consultId) {

        if (consultService.exist(consultId)){
            // 이미 진행완료된 상담 => service 단에서 에러 던짐
            // 상담 무사히 완료
            consultService.delete(consultId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Consult completed!"));
        }
        // 없는 상담
        throw new IllegalArgumentException("존재하지 않는 상담입니다.");
    }
}