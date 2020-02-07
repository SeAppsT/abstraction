package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;

@Viewer
public class RelationComponentViewer {
    public Long relation_id;
    public Long component_id;
    public String name;
    public String type;
    public String mode;
    public String description;
    public String color;

    public RelationComponentViewer (Relation relation, Component component){
        this.relation_id = relation.getId();
        this.component_id = component.getId();
        this.name = component.getName();
        this.type = relation.getType();
        this.description = relation.getDescription();
        this.color = component.getColor();
        if (relation.getComponentTo().getId().equals(component.getId()))
            this.mode = "from";
        else
            this.mode = "to";
    }
}