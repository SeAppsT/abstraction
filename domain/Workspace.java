package com.example.beck.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "workspaces")
@EntityListeners(AuditingEntityListener.class)
public class Workspace extends BaseEntity {

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workspace", cascade = CascadeType.ALL)
    protected List<Component> components;

    public Workspace(Long id){
        this.setId(id);
    }

    public Workspace(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}