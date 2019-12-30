package com.example.beck.web;

import com.example.beck.dto.WorkspaceDto;
import com.example.beck.dto.validation.Add;
import com.example.beck.dto.validation.Edit;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.manager.WorkspaceManager;
import com.example.beck.view.WorkspaceViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Secured("ROLE_USER")
@RequestMapping("/workspace")
public class WorkspaceResource {
    private WorkspaceManager workspaceManager;

    @Autowired
    public WorkspaceResource(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
    }

    @GetMapping
    public List<WorkspaceViewer> getAll() throws EntityNotFoundException {
        return this.workspaceManager.getAllWorkspacesForUser();
    }

    @GetMapping("/{id}")
    public WorkspaceViewer getOne(@PathVariable Long id) throws EntityNotFoundException {
        return this.workspaceManager.getOne(id);
    }

    @PostMapping
    public ResponseEntity addWorkspace(@RequestBody @Validated(Add.class) WorkspaceDto workspaceDto){
        this.workspaceManager.addWorkspace(workspaceDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editWorkspace(@PathVariable Long id, @RequestBody @Validated(Edit.class) WorkspaceDto workspaceDto) throws EntityNotFoundException {
        this.workspaceManager.editWorkspace(workspaceDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity deleteWorkspace(@PathVariable Long id) throws EntityNotFoundException {
        this.workspaceManager.deleteWorkspace(id);
        return ResponseEntity.ok().build();
    }
}