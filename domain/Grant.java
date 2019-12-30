package com.example.beck.domain;

import javax.persistence.*;

@Entity
@Table(name = "grants")
public class Grant extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Privilege privilege;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Workspace workspace;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}