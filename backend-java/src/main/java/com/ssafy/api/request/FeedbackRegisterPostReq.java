package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("FeedbackRegisterPostRequest")
public class FeedbackRegisterPostReq {
    @NotBlank
    @ApiModelProperty(name="피드백 내용", example="abcd")
    String content;

    @NotBlank
    @ApiModelProperty(name="스크립트 id", example="0")
    Long scriptId;
}
