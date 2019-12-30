package com.example.beck.web;

import com.example.beck.dto.ComponentDto;
import com.example.beck.dto.validation.Add;
import com.example.beck.dto.validation.Edit;
import com.example.beck.manager.ComponentManager;
import com.example.beck.view.ComponentViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.beck.exception.*;


@RestController
@RequestMapping("/component")
public class ComponentResource {

    private ComponentManager componentService;

    @Autowired
    public ComponentResource(ComponentManager componentService){
        this.componentService = componentService;
    }

    @GetMapping("/{id}")
    public ComponentViewer getComponent(@PathVariable Long id) throws EntityNotFoundException  {
        return this.componentService.getOneComponent(id);
    }

    @PostMapping
    public ResponseEntity addComponent(@RequestBody @Validated(Add.class) ComponentDto componentDto) throws InvalidPropertyException {
        this.componentService.addComponent(componentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editComponent(@PathVariable Long id, @RequestBody @Validated(Edit.class) ComponentDto componentDto) throws EntityNotFoundException {
        this.componentService.editComponent(componentDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComponent(@PathVariable Long id) throws EntityNotFoundException {
        this.componentService.deleteComponent(id);
        return ResponseEntity.ok().build();
    }
}