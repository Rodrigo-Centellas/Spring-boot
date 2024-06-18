// src/main/java/com/asistencia/models/MateriaDetalles.java
package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "materia_detalles")
@Data
public class MateriaDetalles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
}
