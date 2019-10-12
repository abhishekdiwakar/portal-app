package com.linneaus.portal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Therapy_List")
public class TherapyList {
    @Id
    @Column(name = "therapy_listID")
    private Long id;

    private String name;

    private String dosage;

    @ManyToOne
    @JoinColumn(name = "Medicine_IDmedicine")
    private Medicine medicine;

    @OneToMany(mappedBy = "therapyList")
    private List<Therapy> therapies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public List<Therapy> getTherapies() {
        return therapies;
    }

    public void setTherapies(List<Therapy> therapies) {
        this.therapies = therapies;
    }
}