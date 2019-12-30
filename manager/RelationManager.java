package com.example.beck.manager;

import com.example.beck.domain.Relation;
import com.example.beck.dto.RelationDto;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.exception.InvalidPropertyException;
import com.example.beck.repository.ComponentRepository;
import com.example.beck.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationManager {

    private final RelationRepository relationRepository;
    private final ComponentRepository componentRepository;

    @Autowired
    public RelationManager(RelationRepository relationRepository, ComponentRepository componentRepository) {
        this.relationRepository = relationRepository;
        this.componentRepository = componentRepository;
    }

    public void addRelation(RelationDto relationDto) throws InvalidPropertyException {
        Relation relation = relationDto.cast(new Relation());
        relation.setComponentFrom(this.componentRepository.findById(relationDto.component_from_id).orElseThrow(InvalidPropertyException::new));
        relation.setComponentTo(this.componentRepository.findById(relationDto.component_to_id).orElseThrow(InvalidPropertyException::new));
        this.relationRepository.save(relation);
    }

    public void editRelation(RelationDto relationDto, Long id) throws EntityNotFoundException {
        Relation relation = this.relationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        relation = relationDto.cast(relation);
        this.relationRepository.save(relation);
    }

    public Relation getOneRelation(Long id) throws EntityNotFoundException {
        Relation relation = this.relationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return relation;
    }

    public void deleteRelation(Long id){

    }
}