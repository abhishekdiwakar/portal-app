package com.linneaus.portal.repository;

import com.linneaus.portal.entities.Therapy;
import com.linneaus.portal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {
    List<Therapy> findByDoctor(User doctor);
}
