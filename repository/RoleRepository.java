package com.example.beck.repository;

import com.example.beck.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role> {
    public Role findByName(String name);
}