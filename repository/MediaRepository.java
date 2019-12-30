package com.example.beck.repository;

import com.example.beck.domain.Media;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends BaseRepository<Media>{
    public List<Media> findAllByComponent_Id(Long id);
}