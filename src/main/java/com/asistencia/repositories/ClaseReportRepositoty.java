package com.asistencia.repositories;
import com.asistencia.models.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClaseReportRepositoty extends JpaRepository<Clase, Long> {
}
