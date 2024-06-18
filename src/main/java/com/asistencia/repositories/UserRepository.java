package com.asistencia.repositories;

import com.asistencia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByCi(String ci);
}