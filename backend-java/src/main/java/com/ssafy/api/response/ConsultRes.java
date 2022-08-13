package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Consult;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("ConsultResponse")
public class ConsultRes extends BaseResponseBody {
    Consult consult;
    public static ConsultRes of(Integer statusCode, String message, Consult consult) {
        ConsultRes res = new ConsultRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setConsult(consult);
        return res;
    }
}
