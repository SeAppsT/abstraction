package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;

import java.util.ArrayList;
import java.util.List;

@Viewer
public class SimpleComponentViewer extends Component {

    public List<RelationViewer> relations;
    public List<AnnotationComponentViewer> annotated = new ArrayList<>();

    public SimpleComponentViewer(Component component){
        this.id = component.getId();
        this.name = component.getName();
        this.description = component.getDescription();
        this.color = component.getColor();
        this.num_cell = component.getNum_cell();
    }
}