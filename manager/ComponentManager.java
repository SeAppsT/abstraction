package com.example.beck.manager;

import com.example.beck.domain.Cell;
import com.example.beck.domain.Component;
import com.example.beck.domain.Relation;
import com.example.beck.domain.Workspace;
import com.example.beck.dto.ComponentDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.exception.InvalidPropertyException;
import com.example.beck.repository.*;
import com.example.beck.view.*;
import com.example.beck.view.interfaces.VolumeViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComponentManager{
    private ComponentRepository componentRepository;
    private WorkspaceRepository workspaceRepository;
    private MediaRepository mediaRepository;
    private RelationRepository relationRepository;
    private CellRepository cellRepository;

    @Autowired
    public ComponentManager(ComponentRepository componentRepository, WorkspaceRepository workspaceRepository, MediaRepository mediaRepository, RelationRepository relationRepository, CellRepository cellRepository) {
        this.componentRepository = componentRepository;
        this.workspaceRepository = workspaceRepository;
        this.mediaRepository = mediaRepository;
        this.relationRepository = relationRepository;
        this.cellRepository = cellRepository;
    }

    public void addComponent(ComponentDto componentDto) throws InvalidPropertyException, EntityNotFoundException {
        Component parentComponent = this.componentRepository.findByAttributeAndWorkspace_Id("main", componentDto.workspace_id).orElseThrow(EntityNotFoundException::new);
        this.saveComponent(componentDto, parentComponent);
    }

    public void saveComponent(ComponentDto componentDto, Component parentComponent) throws EntityNotFoundException {
        Component addedComponent = componentDto.cast(new Component());
        Workspace workspace = this.workspaceRepository.findById(componentDto.workspace_id).orElseThrow(EntityNotFoundException::new);
        addedComponent.setWorkspace(workspace);
        this.componentRepository.save(addedComponent);

        Cell cell = new Cell();
        cell.setCord_x(componentDto.x);
        cell.setCord_y(componentDto.y);
        cell.setComponent(addedComponent);
        cell.setInnerComponent(parentComponent);
        this.cellRepository.save(cell);

        Relation abstractionRelation = new Relation();
        abstractionRelation.setComponentFrom(parentComponent);
        abstractionRelation.setComponentTo(addedComponent);
        abstractionRelation.setType("abstraction");
        abstractionRelation.setWorkspace(workspace);
        this.relationRepository.save(abstractionRelation);
    }

    public void addDependComponent(Long id, ComponentDto componentDto) throws EntityNotFoundException {
        Component parentComponent = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.saveComponent(componentDto, parentComponent);
    }

    public ExtendedComponentViewer getOneComponent(Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ExtendedComponentViewer viewer = new ExtendedComponentViewer(component);
        viewer.files = this.mediaRepository.findAllByComponent_Id(component.getId());
        List<Relation> relations = this.relationRepository.findAllByComponentFrom_IdOrComponentTo_Id(component.getId(), component.getId());
        for (Relation relation: relations) {
            RelationComponentViewer rcv = new RelationComponentViewer(relation, this.checkForRelation(relation, component.getId()));
            viewer.relations.add(rcv);
        }
        return viewer;
    }

    public SimpleComponentViewer getSimpleComponent(Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new SimpleComponentViewer(component);
    }

    private Component checkForRelation(Relation relation, Long id){
        if (relation.getComponentTo().getId().equals(id))
            return relation.getComponentFrom();
        else
            return relation.getComponentTo();
    }

    public List<Component> getAllBlocks(Long workspace_id){
        return this.componentRepository.findAllByWorkspace_IdAndType(workspace_id, "block");
    }

    public void editComponent(ComponentDto componentDto, Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        componentDto.cast(component);
        this.componentRepository.save(component);
    }

    public void deleteComponent(Long id) throws EntityNotFoundException {
        if (this.componentRepository.existsById(id))
            this.componentRepository.delete(this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
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

    public void castToAnnotation(Long id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        component.setType("annotation");
        this.componentRepository.save(component);
    }

    public ContainerComponentViewer getLocalAbstractionLevel(Long component_id) throws EntityNotFoundException {
        Component component = this.componentRepository.findById(component_id).orElseThrow(EntityNotFoundException::new);
        ContainerComponentViewer viewer = new ContainerComponentViewer(component);
        return (ContainerComponentViewer) this.fillViewer(component, viewer);
    }

    public VolumeViewer fillViewer(Component component, VolumeViewer viewer){

        component.getLowerRelations().forEach(relation -> { // components
            SimpleComponentViewer scv = new SimpleComponentViewer(relation.getComponentTo());

            Cell cell = this.cellRepository.findByComponent_IdAndInnerComponent_Id(relation.getComponentTo().getId(), component.getId()).orElse(null);
            if (cell == null){
                cell = new Cell();
                cell.setCord_x(0);
                cell.setCord_y(0);
                cell.setComponent(relation.getComponentTo());
                cell.setInnerComponent(component);
                this.cellRepository.save(cell);
            }
            scv.x = cell.getCord_x();
            scv.y = cell.getCord_y();

            for (Relation rel: relation.getComponentTo().getLowerRelations()){
                if (!rel.getComponentTo().getType().equals("annotation")){
                    RelationViewer rv = new RelationViewer(rel);
                    rv.component_id = rel.getComponentTo().getId();
                    scv.relations.add(rv);
                }
            }

            for (Relation rel: relation.getComponentTo().getHigherRelations()){
                if (rel.getComponentFrom().getType().equals("annotation")){
                    scv.annotated.add(new AnnotationComponentViewer(rel.getComponentFrom()));
                }
            }
            viewer.addComponent(scv);
        });

        List<Component> annotations = this.componentRepository.findAllByWorkspace_IdAndType(component.getWorkspace().getId(), "annotation");
        annotations.add(component);
        annotations.forEach(annotation -> {
            viewer.addAnnotation(new AnnotationComponentViewer(annotation));
        });
        return viewer;
    }
}