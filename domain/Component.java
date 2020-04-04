package com.example.beck.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "components")
@EntityListeners(AuditingEntityListener.class)
public class Component extends BaseEntity {

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Workspace workspace;

    @Column(nullable = false)
    protected String type = "block";

    @Column
    protected String color;

    @Column
    private String title_to;

    @Column
    private String title_from;

    @Column
    private String attribute;

    @OneToMany(mappedBy = "componentFrom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Relation> relationsFrom;

    @OneToMany(mappedBy = "componentTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Relation> relationsTo;

    @OneToMany(mappedBy = "component", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cell> cells;

    @OneToMany(mappedBy = "innerComponent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cell> innerCells;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public List<Relation> getLowerRelations() {
        return relationsFrom;
    }

    public void setLowerRelations(List<Relation> relations) {
        this.relationsFrom = relations;
    }

    public List<Relation> getHigherRelations(){
        return this.relationsTo;
    }

    public void setHigherRelations(List<Relation> relations){
        this.relationsTo = relations;
    }

    public String getTitle_to() {
        return title_to;
    }

    public void setTitle_to(String title_to) {
        this.title_to = title_to;
    }

    public String getTitle_from() {
        return title_from;
    }

    public void setTitle_from(String title_from) {
        this.title_from = title_from;
    }

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

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getInnerCells() {
        return innerCells;
    }

    public void setInnerCells(List<Cell> innerCells) {
        this.innerCells = innerCells;
    }
}