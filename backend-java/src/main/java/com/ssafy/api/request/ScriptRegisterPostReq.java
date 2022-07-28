package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("ScriptRegisterPostReq")
public class ScriptRegisterPostReq {
    @NotBlank
    @ApiModelProperty(name="유저 ID", example="ssafy_web")
    Long userId;

    @NotBlank
    @ApiModelProperty(name="문제 ID", example="1")
    Long questionId;

    @NotBlank
    @ApiModelProperty(name="스크립트 내용", example="I am groot")
    String scriptContent;

    @NotBlank
    @ApiModelProperty(name="오디오 URL", example="오디오 URL")
    String audioURL;
}
