package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Relation;

@Viewer
public class RelationViewer {
    public Long id;
    public Long component_id;
    public String type;
    public String name;
    public String color;

    public RelationViewer(Relation relation){
        this.id = relation.getId();
        this.type = relation.getType();
        this.name = relation.getName();
    }
}