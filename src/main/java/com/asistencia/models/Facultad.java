package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "facultades")
@Data
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}
