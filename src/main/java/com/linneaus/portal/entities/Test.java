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
@Table(name = "Test")
public class Test {

    @Id
    @Column(name = "testID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Therapy_IDtherapy")
    private Therapy therapy;

    @OneToMany(mappedBy = "test")
    private List<TestSession> testSessions;
}