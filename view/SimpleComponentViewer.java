package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;

import java.util.ArrayList;
import java.util.List;

@Viewer
public class SimpleComponentViewer {

    public Long id;
    public String name;
    public String description;
    public String type;
    public String color;
    public int num_cell;

    public List<RelationViewer> relations = new ArrayList<>();
    public List<AnnotationComponentViewer> annotated = new ArrayList<>();

    public SimpleComponentViewer(Component component){
        this.id = component.getId();
        this.name = component.getName();
        this.description = component.getDescription();
        this.type = component.getType();
        this.color = component.getColor();
        this.num_cell = component.getNum_cell();
    }
}