package com.example.beck.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "relations")
@EntityListeners(AuditingEntityListener.class)
public class Relation extends BaseEntity {

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;

    @JoinColumn(name = "component_to_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Component componentTo;

    @Column(nullable = false)
    protected String type;

    @JoinColumn(name = "component_from_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Component componentFrom;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Workspace workspace;

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Relation(){}

    public Component getComponentTo() {
        return componentTo;
    }

    public void setComponentTo(Component componentTo) {
        this.componentTo = componentTo;
    }

    public Component getComponentFrom() {
        return componentFrom;
    }

    public void setComponentFrom(Component componentFrom) {
        this.componentFrom = componentFrom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}