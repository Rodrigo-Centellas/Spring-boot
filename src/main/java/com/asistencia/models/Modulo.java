package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "modulos")
@Data
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}
