package com.asistencia.repositories;

import com.asistencia.models.AulaClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaClaseRepository extends JpaRepository<AulaClase, Long> {
    List<AulaClase> findByAulaId(Long aulaId);
}
