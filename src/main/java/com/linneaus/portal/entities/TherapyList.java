package com.linneaus.portal.entities;

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
}