package com.example.beck.repository;

import com.example.beck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    public Optional<User> findByLogin(String login);
}