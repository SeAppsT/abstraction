package com.example.beck.web;

import com.example.beck.dto.RelationDto;
import com.example.beck.dto.validation.Edit;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.exception.InvalidPropertyException;
import com.example.beck.manager.RelationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relation")
public class RelationResource {

    private final RelationManager relationService;

    @Autowired
    public RelationResource(RelationManager relationService) {
        this.relationService = relationService;
    }

    @PostMapping
    public ResponseEntity addRelation(@RequestBody @Validated(Edit.class) RelationDto relationDto) throws InvalidPropertyException {
        this.relationService.addRelation(relationDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editRelation(@PathVariable Long id, @RequestBody @Validated(Edit.class) RelationDto relationDto) throws EntityNotFoundException {
        this.relationService.editRelation(relationDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRelation(@PathVariable Long id){
        this.relationService.deleteRelation(id);
        return ResponseEntity.ok().build();
    }
}