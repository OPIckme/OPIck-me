package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Consult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@ApiModel("ConsultMapResponse")
public class ConsultMapRes extends BaseResponseBody {
    @ApiModelProperty(name = "대기중인 상담 목록")
    Map<Long, Consult> consultMap;

    public static ConsultMapRes of(Integer statusCode, String message, Map<Long, Consult> consultMap) {
        ConsultMapRes res = new ConsultMapRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setConsultMap(consultMap);
        return res;
    }
}