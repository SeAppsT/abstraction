package com.example.beck.web;

import com.example.beck.dto.ComponentPositionDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.manager.ComponentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/component/{id}")
public class ComponentWebService {
    private ComponentManager componentManager;

    @Autowired
    public ComponentWebService(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    @PostMapping("/cast/relationship")
    public ResponseEntity castToRelationship(@PathVariable Long id) throws EntityNotFoundException {
        this.componentManager.castToRelationship(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cast/block")
    public ResponseEntity castToBlock(@PathVariable Long id) throws EntityNotFoundException {
        this.componentManager.castToBlock(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cast/annotation")
    public ResponseEntity castToAnnotation(@PathVariable Long id) throws EntityNotFoundException {
        this.componentManager.castToAnnotation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/position")
    public ResponseEntity changePosition(@PathVariable Long id, @RequestBody @Valid ComponentPositionDto componentPositionDto) throws EntityNotFoundException {
        this.componentManager.changePosition(id, componentPositionDto);
        return ResponseEntity.ok().build();
    }

    //@PostMapping("/position")
    //public ResponseEntity setPosition(@PathVariable Long id, @RequestBody int num_cell) throws EntityNotFoundException {
      //  this.componentManager.setPosition(id, num_cell);
      //  return ResponseEntity.ok().build();
    //}
}