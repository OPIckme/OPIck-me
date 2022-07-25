package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ScriptRegisterPostReq")
public class ScriptRegisterPostReq {

    @ApiModelProperty(name="유저 ID", example="ssafy_web")
    String userId;
    @ApiModelProperty(name="문제 ID", example="1")
    String questionId;
    @ApiModelProperty(name="스크립트 내용", example="I am groot")
    String scriptContent;
    @ApiModelProperty(name="유저 ID", example="오디오 URL")
    String audio;
}
