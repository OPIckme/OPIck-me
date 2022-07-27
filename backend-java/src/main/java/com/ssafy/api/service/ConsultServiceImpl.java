package com.ssafy.api.service;


import com.ssafy.api.request.ConsultRegisterPostReq;
import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;
import com.ssafy.db.repository.ConsultRepository;
import com.ssafy.db.repository.ScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    private final ScriptRepository scriptRepository;

    @Override
    public void create(Script script) {
        Consult consult = new Consult();
        consult.setScript(script);
        consultRepository.save(consult);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consult> waitingList() {
        return consultRepository.findAll().stream().filter(state -> !state.isState()).collect(Collectors.toList());
    }

    @Override
    public void modifyState(Long consultId) {
        Consult findConsult = consultRepository.findById(consultId).get();
        findConsult.setState(true);
        consultRepository.save(findConsult);
    }

    @Override
    public boolean exist(Long consultId) {
        return consultRepository.findById(consultId).isPresent();
    }

    @Override
    public boolean completedStateByScript(Long scriptId) {
        // 이걸로 consult가 찾아지나?
        Consult consult = consultRepository.findByScriptId(scriptId).get();
        return consult.isState();
    }

    @Override
    public boolean completedStateByConsult(Long consultId) {
        Consult consult = consultRepository.findById(consultId).get();
        return consult.isState();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Consult> getConsultByConsultId(Long consultId) {
        return consultRepository.findById(consultId);
    }
}