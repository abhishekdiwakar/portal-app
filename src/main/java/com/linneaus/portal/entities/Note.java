package com.linneaus.portal.entities;

import javax.persistence.*;

@Entity
@Table(name = "Note")
public class Note {

    @Id
    @Column(name = "noteID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Test_Session_IDtest_session")
    private TestSession testSession;

    private String note;

    @ManyToOne
    @JoinColumn(name = "User_IDmed")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestSession getTestSession() {
        return testSession;
    }

    public void setTestSession(TestSession testSession) {
        this.testSession = testSession;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
