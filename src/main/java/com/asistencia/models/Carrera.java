package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carreras")
@Data
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;
}
