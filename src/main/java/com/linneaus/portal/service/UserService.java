package com.linneaus.portal.service;

import com.linneaus.portal.entities.Therapy;
import com.linneaus.portal.entities.TherapyList;
import com.linneaus.portal.entities.User;
import com.linneaus.portal.model.Patient;
import com.linneaus.portal.model.Test;
import com.linneaus.portal.model.TestSession;
import com.linneaus.portal.repository.TherapyRepository;
import com.linneaus.portal.repository.UserRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  TherapyRepository therapyRepository;

  @Autowired
  ResourceLoader resourceLoader;

  public List<Patient> getPatientData(String docName) {
    Set<User> patients = getAllPatientsAssociatedWithDoc(docName);
    List<Patient> patientData = new ArrayList<>();

    for (User patient : patients) {
      List<com.linneaus.portal.model.Therapy> therapies = new ArrayList<>();
      for (Therapy patientTherapy : patient.getPatientTherapies()) {
        List<Test> tests = new ArrayList<>();
        for (com.linneaus.portal.entities.Test test : patientTherapy.getTests()) {
          for (com.linneaus.portal.entities.TestSession testSession : test.getTestSessions()) {
            tests.add(new Test(new Date(), readDataFromFile(testSession.getDataUrl(), testSession.getType())));
          }
        }
        TherapyList therapyList = patientTherapy.getTherapyList();
        com.linneaus.portal.model.Therapy therapy = new com.linneaus.portal.model.Therapy(therapyList.getName(),
            therapyList.getMedicine().getName(), therapyList.getDosage(), tests);
        therapies.add(therapy);
      }

      Patient patientInfo = new Patient(patient.getUserId(), patient.getUsername(), patient.getEmail(), patient.getLat(), patient.getLng(), therapies);
      patientData.add(patientInfo);
    }

    return patientData;
  }

  public Set<User> getAllPatientsAssociatedWithDoc(String docName) {
    User doctor = userRepository.findByUsername(docName);
    Set<User> patients = new HashSet<>();
    doctor.getTherapies().stream().filter(therapy -> patients.add(therapy.getPatient()));
    return patients;
  }


  private List<TestSession> readDataFromFile(String fileName, int testType) {
    List<TestSession> testSessions = new ArrayList<>();
    Resource resource = resourceLoader.getResource("classpath:" + fileName + ".csv");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        if (values.length == 3) {
          testSessions.add(new TestSession(testType, values[0], values[1], values[2]));
        } else {
          testSessions.add(new TestSession(testType, values[0], values[1], values[2], values[3], values[4]));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return testSessions;
  }
}