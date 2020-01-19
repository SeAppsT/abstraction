package com.example.beck.manager;

import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;
import com.example.beck.dto.ComponentDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.exception.InvalidPropertyException;
import com.example.beck.repository.ComponentRepository;
import com.example.beck.repository.MediaRepository;
import com.example.beck.repository.RelationRepository;
import com.example.beck.repository.WorkspaceRepository;
import com.example.beck.view.ExtendedComponentViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ComponentManager{
    private ComponentRepository componentRepository;
    private WorkspaceRepository workspaceRepository;
    private MediaRepository mediaRepository;
    private RelationRepository relationRepository;

    @Autowired
    public ComponentManager(ComponentRepository componentRepository, WorkspaceRepository workspaceRepository, MediaRepository mediaRepository, RelationRepository relationRepository) {
        this.componentRepository = componentRepository;
        this.workspaceRepository = workspaceRepository;
        this.mediaRepository = mediaRepository;
        this.relationRepository = relationRepository;
    }

    public void addComponent(ComponentDto componentDto) throws InvalidPropertyException {
        Component component = componentDto.cast(new Component());
        component.setWorkspace(this.workspaceRepository.findById(componentDto.workspace_id).orElseThrow(InvalidPropertyException::new));
        component.setNum_cell(componentDto.num_cell);
        this.componentRepository.save(component);
    }

    public ExtendedComponentViewer getOneComponent(Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ExtendedComponentViewer viewer = new ExtendedComponentViewer(component);
        viewer.files = this.mediaRepository.findAllByComponent_Id(component.getId());
        List<Relation> relations = new ArrayList<>();
        viewer.relationsAsBlocks = new ArrayList<>();
        viewer.relationsAsRelations = new ArrayList<>();
        relations = this.relationRepository.findAllByComponentFrom_IdOrComponentTo_Id(component.getId(), component.getId());
        for (Relation relation: relations) {
            if (relation.getComponentFrom().getType().equals("block") || relation.getComponentTo().getType().equals("block"))
                viewer.relationsAsBlocks.add(relation);
            else
                viewer.relationsAsRelations.add(relation);
        }
        return viewer;
    }

    public List<Component> getAllBlocks(Long workspace_id){
        return this.componentRepository.findAllByWorkspace_IdAndType(workspace_id, "block");
    }

    public void editComponent(ComponentDto componentDto, Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (componentDto.num_cell != 0)
            component.setNum_cell(componentDto.num_cell);
        componentDto.cast(component);
        this.componentRepository.save(component);
    }

    public void deleteComponent(Long id) throws EntityNotFoundException {
        if (this.componentRepository.existsById(id))
            this.componentRepository.deleteById(id);
        else
            throw new EntityNotFoundException();
    }

    public void castToRelationship(Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        component.setType("relationship");
        this.componentRepository.save(component);
    }

    public void castToBlock(Long id) throws EntityNotFoundException{
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        component.setType("block");
        this.componentRepository.save(component);
    }

    public void setPosition(Long id, int num_cell) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        component.setNum_cell(num_cell);
        this.componentRepository.save(component);
    }
}