package com.example.beck.web;

import com.example.beck.domain.Media;
import com.example.beck.dto.MediaDto;
import com.example.beck.dto.validation.Add;
import com.example.beck.dto.validation.Edit;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.repository.ComponentRepository;
import com.example.beck.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
public class MediaResource {
    private MediaRepository mediaRepository;
    private ComponentRepository componentRepository;

    @Autowired
    public MediaResource(MediaRepository mediaRepository, ComponentRepository componentRepository) {
        this.mediaRepository = mediaRepository;
        this.componentRepository = componentRepository;
    }

    @GetMapping("/{id}")
    public Media getOne(@PathVariable Long id) throws EntityNotFoundException {
        return this.mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    public Media addMedia(@RequestBody @Validated(Add.class) MediaDto mediaDto) throws EntityNotFoundException {
        Media media = mediaDto.cast(new Media());
        media.setComponent(this.componentRepository.findById(mediaDto.component_id).orElseThrow(EntityNotFoundException::new));
        this.mediaRepository.save(media);
        return media;
    }

    @PutMapping("/{id}")
    public ResponseEntity editMedia(@RequestBody @Validated(Edit.class) MediaDto mediaDto, @PathVariable Long id) throws EntityNotFoundException {
        Media media = this.mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        media = mediaDto.cast(media);
        this.mediaRepository.save(media);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMedia(@PathVariable Long id) throws EntityNotFoundException {
        if (this.mediaRepository.existsById(id))
            this.mediaRepository.deleteById(id);
        else
            throw new EntityNotFoundException();
        return ResponseEntity.ok().build();
    }
}