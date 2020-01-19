package com.example.beck.view;


import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;
import com.example.beck.domain.Workspace;

import java.util.List;

@Viewer
public class WorkspaceViewer extends Workspace {

    public List<SimpleComponentViewer> components;

    public WorkspaceViewer(Workspace workspace){
        this.id = workspace.getId();
        this.description = workspace.getDescription();
        this.name = workspace.getName();
    }
}