package com.ssafy.db.repository;

import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

}
