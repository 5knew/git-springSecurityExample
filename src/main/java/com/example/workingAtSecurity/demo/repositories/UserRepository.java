package com.example.workingAtSecurity.demo.repositories;

import com.example.workingAtSecurity.demo.model.User;
import com.example.workingAtSecurity.demo.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    User findByRoles(Role role);
}
