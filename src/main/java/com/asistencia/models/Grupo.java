package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Table(name = "grupos")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "grupo")
    private Set<Clase> clases;



    //para reporte

    public Long getId() {
        return id;
    }
}
