package com.example.beck.repository;

import com.example.beck.domain.Cell;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CellRepository extends BaseRepository<Cell>{
    public Optional<Cell> findByComponent_IdAndInnerComponent_Id(Long component_id, Long inner_component_id);
}