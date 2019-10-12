package com.linneaus.portal.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Test")
public class Test {

    @Id
    @Column(name = "testID")
    private Long id;

    @Column(name = "startTime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @ManyToOne
    @JoinColumn(name = "Therapy_IDtherapy")
    private Therapy therapy;

    @OneToMany(mappedBy = "")
    private List<TestSession> testSessions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Therapy getTherapy() {
        return therapy;
    }

    public void setTherapy(Therapy therapy) {
        this.therapy = therapy;
    }

    public List<TestSession> getTestSessions() {
        return testSessions;
    }

    public void setTestSessions(List<TestSession> testSessions) {
        this.testSessions = testSessions;
    }
}
