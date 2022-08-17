package com.ssafy.api.controller;

import com.ssafy.api.request.ScriptModifyPutReq;
import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.api.response.ScriptDetailRes;
import com.ssafy.api.response.ScriptListRes;
import com.ssafy.api.service.QuestionService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.converter.Converter;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;
//import com.ssafy.stt.STT;
import com.ssafy.stt.STT2;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import java.util.List;
import java.util.Optional;


@Api(value = "스크립트 API", tags = {"Script."})
@RestController
@RequestMapping("/api/v1/script")
@RequiredArgsConstructor
@Slf4j
public class ScriptController {
    private final ScriptService scriptService;
    private final UserService userService;
    private final QuestionService questionService;
    private final Converter converter;
    private final STT2 stt;
    
    @PostMapping
    @ApiOperation(value = "스크립트 등록", notes = "userId,questionId,content, audiourl을 받아서 스크립트를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity createScript(@RequestBody @ApiParam(value="스크립트 등록 정보", required = true) @Validated ScriptRegisterPostReq scriptRegisterPostReq) throws Exception{
        Long userId = scriptRegisterPostReq.getUserId();
        Long questionId = scriptRegisterPostReq.getQuestionId();

        //userService에 구현 필요
        Optional<User> user = userService.getUserByUserId(userId);
        Optional<Question> question = questionService.getQuestionByQuestionId(questionId);

        String audioUrl = scriptRegisterPostReq.getAudioURL();
        String keyName = scriptRegisterPostReq.getKeyName();
        String fileName=keyName+".webm";
        String targetName=keyName+".wav";

        // aws에서 로컬에 오디오파일 다운
        scriptService.getAudio(fileName);
        // 오디오파일 변환
        converter.webm2mp3(fileName,targetName);

//        String content = stt.asyncRecognizeFile(targetName);
        String content = stt.recognizeFromMicrophone(targetName);
        System.out.println("content = " + content);
        deleteAudioFile(fileName);
        deleteAudioFile(targetName);
        scriptService.createScript(user.get(), question.get(),content,audioUrl);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201,"스크립트 추가 성공"));
    }

    @GetMapping("/{username}")
    @ApiOperation(value = "전체 스크립트 조회", notes = "모든 스크립트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ScriptListRes.class),
    })
    public ResponseEntity<ScriptListRes> getList(@PathVariable String username){
        User user = userService.getUserByUsername(username).get();
        Long userId = user.getId();
        List<Script> scriptList = scriptService.getScriptList(userId);

        return ResponseEntity.status(200).body(ScriptListRes.of(200,"스크립트 리스트 불러오기 성공!",scriptList));
    }

    @GetMapping("/{username}/{scriptId}")
    @ApiOperation(value = "스크립트 디테일 조회", notes = "스크립트 id로 디테일 조회.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ScriptDetailRes.class),
    })
    public ResponseEntity<ScriptDetailRes> getScriptDetail(@PathVariable String username, @PathVariable Long scriptId){
        User user = userService.getUserByUsername(username).get();
        Long userId = user.getId();
        Script script = scriptService.getDetail(scriptId, userId).get();

        return ResponseEntity.status(200).body(ScriptDetailRes.of(200,"스크립트 세부사항 불러오기 성공!",script));
    }

    @DeleteMapping("/{scriptId}")
    @ApiOperation(value = "스크립트 삭제", notes = "스크립트 한개 삭제.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> delete(@PathVariable Long scriptId){
        scriptService.deleteByScriptId(scriptId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"스크립트 삭제 성공!!"));
    }

    @PutMapping("/edit/{scriptId}")
    @ApiOperation(value = "스크립트 수정", notes = "스크립트 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
    })
    public ResponseEntity<? extends BaseResponseBody> scriptModify(@PathVariable Long scriptId, @RequestBody ScriptModifyPutReq scriptModifyPutReq) {
        String content = scriptModifyPutReq.getScriptContent();
        Script script = scriptService.modifyScript(scriptId, content);
        return ResponseEntity.status(200).body(ScriptDetailRes.of(200,"Script modified!",script));
    }


    public void deleteAudioFile(String filePath) {
        File file = new File(filePath);

        if( file.exists() ){
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }

    }

}
