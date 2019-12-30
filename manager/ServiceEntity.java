package com.example.beck.manager;

import com.example.beck.domain.BaseEntity;
import com.example.beck.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class ServiceEntity {
    private final BaseRepository<BaseEntity> baseRepository;

    @Autowired
    public ServiceEntity(BaseRepository<BaseEntity> baseRepository) {
        this.baseRepository = baseRepository;
    }
    
    public void add(BaseEntity entity){
        this.baseRepository.save(entity);
    }

    public void edit(BaseEntity entity){
        this.baseRepository.save(entity);
    }

    public void delete(){

    }
}