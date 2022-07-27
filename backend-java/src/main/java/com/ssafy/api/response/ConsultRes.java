package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Script;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class ConsultRes extends BaseResponseBody {
    private Long id;
    private Script script;
    private Timestamp createdAt;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean state;
}