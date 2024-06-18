package com.asistencia.repositories;

import com.asistencia.models.HorarioClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioClaseRepository extends JpaRepository<HorarioClase, Long> {
    List<HorarioClase> findByHorarioId(Long horarioId);
    List<HorarioClase> findByClaseUserId(Long userId);
}
