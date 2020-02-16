package com.example.beck.manager;

import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;
import com.example.beck.domain.Workspace;
import com.example.beck.dto.WorkspaceDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.repository.ComponentRepository;
import com.example.beck.repository.RelationRepository;
import com.example.beck.repository.WorkspaceRepository;
import com.example.beck.provider.AuditorAwareProvider;
import com.example.beck.view.AnnotationComponentViewer;
import com.example.beck.view.RelationViewer;
import com.example.beck.view.SimpleComponentViewer;
import com.example.beck.view.WorkspaceViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkspaceManager {
    private WorkspaceRepository workspaceRepository;

    private ComponentRepository componentRepository;
    private AuditorAwareProvider awareProvider;
    private RelationRepository relationRepository;

    @Autowired
    public WorkspaceManager(WorkspaceRepository workspaceRepository, ComponentRepository componentRepository, AuditorAwareProvider awareProvider, RelationRepository relationRepository) {
        this.workspaceRepository = workspaceRepository;
        this.componentRepository = componentRepository;
        this.awareProvider = awareProvider;
        this.relationRepository = relationRepository;
    }


    public List<WorkspaceViewer> getAllWorkspacesForUser() throws EntityNotFoundException {
        List<Workspace> workspaces = this.workspaceRepository.getAllByUser_Id(
                awareProvider.getCurrentAuditor()
                        .orElseThrow(EntityNotFoundException::new).getId());
        List<WorkspaceViewer> viewers = new ArrayList<>();
        workspaces.forEach(workspace -> {
            viewers.add(new WorkspaceViewer(workspace));
        });
        return viewers;
    }

    public WorkspaceViewer getOne(Long id) throws EntityNotFoundException {
        Workspace workspace = this.workspaceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        WorkspaceViewer viewer = new WorkspaceViewer(workspace);
        // get from DB
        List<Component> components = this.componentRepository.findAllByWorkspace_IdAndTypeNot(workspace.getId(), "annotation");
        List<Component> annotations = this.componentRepository.findAllByWorkspace_IdAndType(workspace.getId(), "annotation");
        // List<Component> namedRelations = this.componentRepository.findAllByWorkspace_IdAndType(workspace.getId(), "relationship");

        // processing
        for (Component component: components) {
            SimpleComponentViewer scv = new SimpleComponentViewer(component);
            for (Relation relation: component.getLowerRelations()){
                if (relation.getComponentTo().getType().equals("annotation"))
                    scv.annotated.add(new AnnotationComponentViewer(relation.getComponentTo()));
                else {
                    RelationViewer rv = new RelationViewer(relation);
                    rv.component_id = relation.getComponentTo().getId();
                    scv.relations.add(rv);
                }
            }
            viewer.components.add(scv);
        }
        annotations.forEach(annotation -> {
            viewer.annotations.add(new AnnotationComponentViewer(annotation));
        });
        return viewer;
    }

    public void addWorkspace(WorkspaceDto workspaceDto){
        Workspace workspace = workspaceDto.cast(new Workspace());
        this.workspaceRepository.save(workspace);
    }

    public void editWorkspace(WorkspaceDto workspaceDto, Long id) throws EntityNotFoundException {
        Workspace workspace = this.workspaceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        workspace = workspaceDto.cast(workspace);
        this.workspaceRepository.save(workspace);
    }

    public void deleteWorkspace(Long id) throws EntityNotFoundException {
        if (this.workspaceRepository.existsById(id))
            this.workspaceRepository.deleteById(id);
        else
            throw new EntityNotFoundException();
    }
}