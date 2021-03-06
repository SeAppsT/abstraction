package com.example.beck.web;

import com.example.beck.domain.Component;
import com.example.beck.dto.ComponentDto;
import com.example.beck.dto.validation.Add;
import com.example.beck.dto.validation.Edit;
import com.example.beck.manager.ComponentManager;
import com.example.beck.view.ContainerComponentViewer;
import com.example.beck.view.ExtendedComponentViewer;
import com.example.beck.view.SimpleComponentViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.beck.exception.*;

import java.util.List;


@RestController
@RequestMapping("/component")
public class ComponentResource {

    private ComponentManager componentService;

    @Autowired
    public ComponentResource(ComponentManager componentService){
        this.componentService = componentService;
    }

    @GetMapping("/{id}")
    public ExtendedComponentViewer getComponent(@PathVariable Long id) throws EntityNotFoundException  {
        return this.componentService.getOneComponent(id);
    }

    @GetMapping("/{workspace_id}/block")
    public List<Component> getBlocksByWorkspace(@PathVariable Long workspace_id){
        return this.componentService.getAllBlocks(workspace_id);
    }

    @GetMapping("/{component_id}/local")
    public ContainerComponentViewer getLocalAbstractionLevel(@PathVariable Long component_id) throws EntityNotFoundException {
        return this.componentService.getLocalAbstractionLevel(component_id);
    }

    @PostMapping
    public ResponseEntity addComponent(@RequestBody @Validated(Add.class) ComponentDto componentDto) throws InvalidPropertyException, EntityNotFoundException {
        this.componentService.addComponent(componentDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{component_id}")
    public ResponseEntity addDependComponent(@PathVariable Long component_id, @RequestBody @Validated(Add.class) ComponentDto componentDto) throws InvalidPropertyException, EntityNotFoundException {
        this.componentService.addDependComponent(component_id, componentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editComponent(@PathVariable Long id, @RequestBody @Validated(Edit.class) ComponentDto componentDto) throws EntityNotFoundException {
        this.componentService.editComponent(componentDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComponent(@PathVariable Long id) throws EntityNotFoundException, UndeletebleEntityException {
        this.componentService.deleteComponent(id);
        return ResponseEntity.ok().build();
    }
}