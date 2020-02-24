package com.example.beck.manager;

import com.example.beck.domain.Cell;
import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;
import com.example.beck.domain.Workspace;
import com.example.beck.dto.WorkspaceDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.repository.CellRepository;
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
import java.util.List;

@Service
public class WorkspaceManager {

    private WorkspaceRepository workspaceRepository;
    private ComponentRepository componentRepository;
    private AuditorAwareProvider awareProvider;
    private RelationRepository relationRepository;
    private CellRepository cellRepository;
    private ComponentManager componentManager;

    @Autowired
    public WorkspaceManager(WorkspaceRepository workspaceRepository, ComponentRepository componentRepository, AuditorAwareProvider awareProvider, RelationRepository relationRepository, CellRepository cellRepository, ComponentManager componentManager) {
        this.workspaceRepository = workspaceRepository;
        this.componentRepository = componentRepository;
        this.awareProvider = awareProvider;
        this.relationRepository = relationRepository;
        this.cellRepository = cellRepository;
        this.componentManager = componentManager;
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
        Component component = this.componentRepository.findByAttributeAndWorkspace_Id("main", workspace.getId()).orElseThrow(EntityNotFoundException::new);
        return (WorkspaceViewer) this.componentManager.fillViewer(component, viewer);
    }

    public void addWorkspace(WorkspaceDto workspaceDto){
        Workspace workspace = workspaceDto.cast(new Workspace());

        this.workspaceRepository.save(workspace);

        Component component = new Component();

        component.setName("Корень");
        component.setType("block");
        component.setWorkspace(workspace);
        component.setColor("#000000");
        component.setAttribute("main");

        this.componentRepository.save(component);

        Cell cell = new Cell();
        cell.setComponent(component);
        cell.setInnerComponent(null);
        cell.setCord_x(0);
        cell.setCord_y(0);

        this.cellRepository.save(cell);
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