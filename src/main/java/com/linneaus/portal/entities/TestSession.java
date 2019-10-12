package com.linneaus.portal.entities;

import javax.persistence.*;

@Entity
@Table(name = "TestSession")
public class TestSession {

    @Id
    @Column(name = "test_SessionID")
    private Long id;

    @Column(name = "test_type")
    private int type;

    @ManyToOne
    @JoinColumn(name = "Test_IDtest")
    private Test test;

    @Column(name = "DataURL")
    private String dataUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}
