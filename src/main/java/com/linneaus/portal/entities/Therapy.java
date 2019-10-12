package com.linneaus.portal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Therapy")
public class Therapy {
    @Id
    @Column(name = "therapyID")
    private Long therapyId;

    @OneToOne
    private User patient;

    @ManyToOne
    @JoinColumn(name = "User_IDmed")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "TherapyList_IDtherapylist")
    private TherapyList therapyList;

    @OneToMany(mappedBy="therapy")
    private List<Test> tests;

    public Long getTherapyId() {
        return therapyId;
    }

    public void setTherapyId(Long therapyId) {
        this.therapyId = therapyId;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public TherapyList getTherapyList() {
        return therapyList;
    }

    public void setTherapyList(TherapyList therapyList) {
        this.therapyList = therapyList;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
