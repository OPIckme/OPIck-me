package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("ScriptModifyPutReq")
public class ScriptModifyPutReq {
    @NotBlank(message = "스크립트 내용은 비어있을 수 없습니다.")
    @ApiModelProperty(name="스크립트 내용", example="I am groot")
    String scriptContent;
}