package com.example.beck.view;

import com.example.beck.annotations.Viewer;
import com.example.beck.domain.Component;
import com.example.beck.domain.Media;
import com.example.beck.domain.Relation;

import java.util.List;

@Viewer
public class ExtendedComponentViewer extends Component {

    public List<Media> files;
    public List<Relation> relationsAsBlocks;
    public List<Relation> relationsAsRelations;
    public Long workspace_id;

    public ExtendedComponentViewer(Component component){
        this.id = component.getId();
        this.name = component.getName();
        this.description = component.getDescription();
        this.num_cell = component.getNum_cell();
        this.workspace_id = component.getWorkspace().getId();
    }
}