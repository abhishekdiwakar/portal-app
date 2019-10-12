package com.linneaus.portal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Note")
public class Note {
    @Id
    @Column(name = "noteID")
    private Long id;

    private String note;

    @ManyToOne
    @JoinColumn(name = "Test_Session_IDtest_session")
    private TestSession testSession;

    @ManyToOne
    @JoinColumn(name = "User_IDmed")
    private User user;
}