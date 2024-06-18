package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aulas")
@Data
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer capacidad;


    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;
}
