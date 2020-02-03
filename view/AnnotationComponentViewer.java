package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;

@Viewer
public class AnnotationComponentViewer {
    public Long id;
    public String name;
    public String color;

    public AnnotationComponentViewer(Component component){
        this.id = component.getId();
        this.name = component.getName();
        this.color = component.getColor();
    }
}