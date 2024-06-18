package com.asistencia.repositories;

import com.asistencia.models.Clase;
import com.asistencia.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
