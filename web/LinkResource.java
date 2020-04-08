package com.example.beck.web;

import com.example.beck.domain.Link;
import com.example.beck.dto.LinkDto;
import com.example.beck.dto.validation.Add;
import com.example.beck.dto.validation.Edit;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.repository.ComponentRepository;
import com.example.beck.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkResource {
    private LinkRepository linkRepository;
    private ComponentRepository componentRepository;

    @Autowired
    public LinkResource(LinkRepository linkRepository, ComponentRepository componentRepository) {
        this.linkRepository = linkRepository;
        this.componentRepository = componentRepository;
    }

    @PostMapping
    public ResponseEntity addLink(@RequestBody @Validated(Add.class) LinkDto linkDto) throws EntityNotFoundException {
        Link link = linkDto.cast(new Link());
        link.setComponent(this.componentRepository.findById(linkDto.component_id).orElseThrow(EntityNotFoundException::new));
        this.linkRepository.save(link);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editLink(@PathVariable Long id, @RequestBody @Validated(Edit.class) LinkDto linkDto) throws EntityNotFoundException {
        Link link = this.linkRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        link = linkDto.cast(link);
        this.linkRepository.save(link);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLink(@PathVariable Long id) throws EntityNotFoundException {
        if (this.linkRepository.existsById(id))
            this.linkRepository.deleteById(id);
        else
            throw new EntityNotFoundException();
        return ResponseEntity.ok().build();
    }
}