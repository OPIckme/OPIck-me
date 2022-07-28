package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class QuestionRes extends BaseResponseBody {
    @ApiModelProperty(name="id", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    private Long id;
    private String topic;
    private String questionContent;
    private String level;
    private String audioUrl;
}
