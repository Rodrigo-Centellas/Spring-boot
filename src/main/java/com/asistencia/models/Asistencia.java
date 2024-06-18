package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "asistencias")
@Data
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private LocalTime hora_ingreso;
    private LocalTime hora_salida;
    private Integer minutos_atraso;
    private LocalDate fecha;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}
