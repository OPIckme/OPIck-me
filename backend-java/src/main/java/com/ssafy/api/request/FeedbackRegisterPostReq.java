package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRegisterPostReq {
    String content;
    Long scriptId;
}
