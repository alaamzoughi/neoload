package com.alaa.auth2.repo;

import com.alaa.auth2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findById(Integer id) ;
    User findByUsername (String username) ;
    void deleteById(Integer id);
    User findByName (String name) ;
}
