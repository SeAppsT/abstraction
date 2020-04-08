package com.example.beck.domain;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    @Column
    private String link;

    @Column
    private String type;

    @JoinColumn
    @ManyToOne
    private Component component;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}