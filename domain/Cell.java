package com.example.beck.domain;

import javax.persistence.*;

@Entity
@Table
public class Cell extends BaseEntity{

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Component component;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Component innerComponent;

    @Column
    private int cord_x;

    @Column
    private int cord_y;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Component getInnerComponent() {
        return innerComponent;
    }

    public void setInnerComponent(Component innerComponent) {
        this.innerComponent = innerComponent;
    }

    public int getCord_x() {
        return cord_x;
    }

    public void setCord_x(int cord_x) {
        this.cord_x = cord_x;
    }

    public int getCord_y() {
        return cord_y;
    }

    public void setCord_y(int cord_y) {
        this.cord_y = cord_y;
    }
}