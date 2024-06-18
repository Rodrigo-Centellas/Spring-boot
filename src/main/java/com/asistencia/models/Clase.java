package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Table(name = "clases")
@Data
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cupo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"clases"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    @JsonIgnoreProperties({"clases"})
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    @JsonIgnoreProperties({"clases"})
    private Materia materia;


    //para reporte

    @OneToMany(mappedBy = "clase", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"clase"})
    private Set<HorarioClase> horarioClases;

}
