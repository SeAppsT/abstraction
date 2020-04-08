package com.example.beck.view;


import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Workspace;
import com.example.beck.view.interfaces.VolumeViewer;

import java.util.ArrayList;
import java.util.List;

@Viewer
public class WorkspaceViewer implements VolumeViewer {

    public Long id;
    public String description;
    public String name;
    public List<SimpleComponentViewer> components = new ArrayList<>();
    public List<AnnotationComponentViewer> annotations = new ArrayList<>();

    public WorkspaceViewer(Workspace workspace){
        this.id = workspace.getId();
        this.description = workspace.getDescription();
        this.name = workspace.getName();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<SimpleComponentViewer> getComponents() {
        return components;
    }

    public List<AnnotationComponentViewer> getAnnotations() {
        return annotations;
    }

    @Override
    public void addComponent(SimpleComponentViewer viewer) {
        this.components.add(viewer);
    }

    @Override
    public void addAnnotation(AnnotationComponentViewer viewer) {
        this.annotations.add(viewer);
    }
}