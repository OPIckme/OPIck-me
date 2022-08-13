package com.ssafy.api.service;

import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;
import com.ssafy.db.repository.ConsultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    @Override
    public Consult create(Script script, String room) {
        Consult consult = new Consult();
        consult.setScript(script);
        consult.setRoom(room);
        return consultRepository.save(consult);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Long, Consult> waitingConsultMap() {
        return consultRepository.findAll().stream().collect(Collectors.toMap(Consult::getId, v -> v ));
    }



    @Override
    public boolean exist(Long consultId) {
        return consultRepository.findById(consultId).isPresent();
    }

    @Override
    public void delete(Long consultId) {
        consultRepository.deleteById(consultId);
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Consult> getConsultByConsultId(Long consultId) {
        return consultRepository.findById(consultId);
    }
}