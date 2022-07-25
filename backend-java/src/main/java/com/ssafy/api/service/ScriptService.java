package com.ssafy.api.service;

import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.db.entity.Script;

public interface ScriptService {
    Script createScript(ScriptRegisterPostReq scriptRegisterPostReq);

    Script getScriptByScriptId(String scriptId);
}
