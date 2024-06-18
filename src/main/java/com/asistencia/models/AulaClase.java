package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aula_clase")
@Data
public class AulaClase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
}
