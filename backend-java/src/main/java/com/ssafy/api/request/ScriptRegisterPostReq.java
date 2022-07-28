package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("ScriptRegisterPostReq")
public class ScriptRegisterPostReq {
    @NotNull
    @ApiModelProperty(name="유저 ID", example="1")
    Long userId;

    @NotNull
    @ApiModelProperty(name="문제 ID", example="1")
    Long questionId;

    @NotBlank
    @ApiModelProperty(name="스크립트 내용", example="I am groot")
    String scriptContent;

    @NotBlank
    @ApiModelProperty(name="오디오 URL", example="오디오 URL")
    String audioURL;
}
