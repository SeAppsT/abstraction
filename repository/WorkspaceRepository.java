package com.example.beck.repository;

import com.example.beck.domain.User;
import com.example.beck.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends BaseRepository<Workspace> {
    public List<Workspace> getAllByUser_Id(Long id);
}