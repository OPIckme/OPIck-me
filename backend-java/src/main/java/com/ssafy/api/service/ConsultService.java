package com.ssafy.api.service;

import com.ssafy.api.request.ConsultRegisterPostReq;
import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;

import java.util.List;
import java.util.Optional;


public interface ConsultService {
    void create(Script script);

    List<Consult> waitingList();

    boolean completedStateByScript(Long scriptId);

    boolean completedStateByConsult(Long consultId);

    void modifyState(Long consultId);

    boolean exist(Long consultId);

    Optional<Consult> getConsultByConsultId(Long consultId);
}