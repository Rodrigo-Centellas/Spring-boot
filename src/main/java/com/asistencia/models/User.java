package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String rol;
    private String ci;
    private String direccion;

    @OneToMany(mappedBy = "user")
    private Set<Clase> clases;





    //para reportes

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
