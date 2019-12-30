package com.example.beck.repository;

import com.example.beck.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    default <E extends T> void safeDelete(E entity){
        entity.setStatus("DELETED");
        save(entity);
    }
}