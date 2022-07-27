package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class QuestionRes extends BaseResponseBody {
    private Long id;
    private String topic;
    private String questionContent;
    private String level;
    private String audioUrl;
}
