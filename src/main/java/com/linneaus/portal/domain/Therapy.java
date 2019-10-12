package com.linneaus.portal.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Therapy")
public class Therapy {
    @Id
    @Column(name = "therapyID")
    private Long therapyId;

    @ManyToOne
    @JoinColumn(name = "User_IDpatient")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "User_IDmed")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private TherapyList therapyList;

    @OneToMany(mappedBy = "therapy")
    private List<Test> tests;
}