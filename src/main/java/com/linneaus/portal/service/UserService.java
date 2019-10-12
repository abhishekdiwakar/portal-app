package com.linneaus.portal.service;

import com.linneaus.portal.entities.Therapy;
import com.linneaus.portal.entities.User;
import com.linneaus.portal.repository.TherapyRepository;
import com.linneaus.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    TherapyRepository therapyRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

   public void getPatientData(User doctor) {
       List<Therapy> therapies = therapyRepository.findByDoctor(doctor);

   }
}
