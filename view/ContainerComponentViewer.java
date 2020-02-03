package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;

import java.util.ArrayList;
import java.util.List;

@Viewer
public class ContainerComponentViewer {
    public Long id;
    public String name;
    public List<SimpleComponentViewer> components = new ArrayList<>();

    public ContainerComponentViewer(Component component){
        this.id = component.getId();
        this.name = component.getName();
    }
}