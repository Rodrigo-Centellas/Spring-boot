package com.asistencia.controllers;

import com.asistencia.models.AulaClase;
import com.asistencia.services.AulaClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aula_clase")
public class AulaClaseController {
    @Autowired
    private AulaClaseService aulaClaseService;

    @GetMapping
    public List<AulaClase> findAll() {
        return aulaClaseService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AulaClase aulaClase) {
        try {
            return ResponseEntity.ok(aulaClaseService.save(aulaClase));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        aulaClaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
