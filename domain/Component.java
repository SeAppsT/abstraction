package com.example.beck.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "components")
@EntityListeners(AuditingEntityListener.class)
public class Component extends BaseEntity{

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;

    @Column
    protected int num_cell;

    @Column(nullable = false)
    protected String type = "block";

    @Column
    protected String color;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Workspace workspace;

    /*
    @OneToMany(mappedBy = "component_to", fetch = FetchType.LAZY)
    protected List<Relation> relations_to;

    @OneToMany(mappedBy = "component_from", fetch = FetchType.LAZY)
    protected List<Relation> relations_from;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "component")
    protected List<Media> files;
    */

    public Component(Long id){
        this.setId(id);
    }

    public Component(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public int getNum_cell() {
        return num_cell;
    }

    public void setNum_cell(int num_cell) {
        this.num_cell = num_cell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}