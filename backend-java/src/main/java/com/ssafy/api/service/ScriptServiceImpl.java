package com.ssafy.api.service;

import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.db.entity.Script;
import com.ssafy.db.repository.ScriptRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("scriptService")
@Transactional
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    private final UserRepository userRepository;


    @Override
    public Script createScript(ScriptRegisterPostReq scriptRegisterPostReq) {
        Script script = new Script();


        script.setScriptContent(scriptRegisterPostReq.getScriptContent());
        script.setAudioUrl(scriptRegisterPostReq.getAudio());


        return scriptRepository.save(script);
    }

    @Override
    public Script getScriptByScriptId(String scriptId) {
        Script script = scriptRepository.findByScriptId(scriptId).get();

        return script;
    }
}