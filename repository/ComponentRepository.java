package com.example.beck.repository;

import com.example.beck.domain.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponentRepository extends BaseRepository<Component> {
    public List<Component> findAllByWorkspace_Id(Long id);
    public List<Component> findAllByWorkspace_IdAndType(Long workspace_id, String type);
}