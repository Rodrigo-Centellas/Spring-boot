package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Table(name = "materias")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String sigla;
    private Integer horas;
    private Integer nivel;

    @OneToMany(mappedBy = "materia")
    private Set<Clase> clases;

    //para reporte

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
