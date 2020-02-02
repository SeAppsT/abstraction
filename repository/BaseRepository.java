package com.example.beck.repository;

import com.example.beck.domain.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    default <E extends T> void safeDelete(E entity){
        entity.setStatus("DELETED");
        save(entity);
    }

    /*public List<T> findAllByStatus(String status);

    default List<T> findAll(){
        return this.findAllByStatus("ACTIVE");
    }*/
}