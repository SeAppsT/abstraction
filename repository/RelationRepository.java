package com.example.beck.repository;

import com.example.beck.domain.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends BaseRepository<Relation> {
    public List<Relation> findAllByComponentFrom_IdOrComponentTo_Id(Long component_from_id, Long component_to_id);
    public List<Relation> findAllByWorkspace_Id(Long id);
    public List<Relation> findAllByComponentFrom_Id(Long component_id);
    public List<Relation> findAllByWorkspace_IdAndComponentFrom_AttributeAndComponentTo_TypeNot(Long workspace_id, String attribute, String type);
    // I'm so sorry...
}