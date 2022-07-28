package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("ConsultRegisterPostRequest")
public class ConsultRegisterPostReq {
    @NotBlank
    @ApiModelProperty(name="스크립트 ID", example="13")
    Long scriptId;
}