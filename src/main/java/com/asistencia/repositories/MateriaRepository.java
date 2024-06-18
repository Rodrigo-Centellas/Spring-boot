// src/main/java/com/asistencia/repositories/MateriaRepository.java
package com.asistencia.repositories;

import com.asistencia.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}
