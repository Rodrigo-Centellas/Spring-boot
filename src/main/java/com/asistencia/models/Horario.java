package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "horarios")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime horarioInicio;
    private LocalTime  horarioFin;
    private String dia;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @OneToMany(mappedBy = "horario")
    private Set<HorarioClase> horarioClases;
}
