package com.asistencia.controllers;

import com.asistencia.models.HorarioClase;
import com.asistencia.services.HorarioClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/horario_clase")
public class HorarioClaseController {
    @Autowired
    private HorarioClaseService horarioClaseService;

    @GetMapping
    public List<HorarioClase> findAll() {
        return horarioClaseService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody HorarioClase horarioClase) {
        try {
            return ResponseEntity.ok(horarioClaseService.save(horarioClase));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        horarioClaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
