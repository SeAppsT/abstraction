package com.example.beck.repository;

import com.example.beck.domain.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends BaseRepository<Link> {
    public List<Link> findAllByComponent_Id(Long id);
}