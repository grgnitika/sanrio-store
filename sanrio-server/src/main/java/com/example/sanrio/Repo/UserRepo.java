package com.example.sanrio.Repo;

import com.example.sanrio.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User,Long>{

    Optional<User> findByEmail(String username);

    Optional<User> findByEmailAndSecurityQuestion(String email, String securityQuestion);

}

