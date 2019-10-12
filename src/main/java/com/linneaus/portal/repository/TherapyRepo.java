package com.linneaus.portal.repository;

import com.linneaus.portal.domain.Therapy;
import com.linneaus.portal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TherapyRepo extends JpaRepository<Therapy, Long> {
    List<Therapy> findByDoctor(User doctor);
}
