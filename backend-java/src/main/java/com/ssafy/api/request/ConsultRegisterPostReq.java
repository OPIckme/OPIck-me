package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConsultRegisterPostRequest")
public class ConsultRegisterPostReq {
    @ApiModelProperty(name="스크립트 ID", example="13")
    Long scriptId;
}