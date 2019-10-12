package com.linneaus.portal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
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
}
