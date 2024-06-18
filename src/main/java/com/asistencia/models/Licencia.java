package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "licencias")
@Data
public class Licencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private String descripcion;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
