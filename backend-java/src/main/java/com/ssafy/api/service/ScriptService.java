package com.ssafy.api.service;

import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.db.entity.Script;

import java.util.Optional;

public interface ScriptService {
    Script createScript(ScriptRegisterPostReq scriptRegisterPostReq);

    Optional<Script> getScriptByScriptId(Long scriptId);
}
