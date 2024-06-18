package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "horario_clase")
@Data
public class HorarioClase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    @JsonIgnoreProperties({"horarioClases"})
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    @JsonIgnoreProperties({"horarioClases"})
    private Horario horario;
}
