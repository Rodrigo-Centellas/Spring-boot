package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "feriados")
@Data
public class Feriado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
