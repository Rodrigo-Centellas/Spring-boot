// src/main/java/com/asistencia/controllers/MateriaDetallesController.java
package com.asistencia.controllers;

import com.asistencia.models.MateriaDetalles;
import com.asistencia.services.MateriaDetallesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materia-detalles")
public class MateriaDetallesController {

    @Autowired
    private MateriaDetallesService materiaDetallesService;

    @GetMapping
    public List<MateriaDetalles> getAllMateriaDetalles() {
        return materiaDetallesService.getAllMateriaDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDetalles> getMateriaDetallesById(@PathVariable Long id) {
        MateriaDetalles materiaDetalles = materiaDetallesService.getMateriaDetallesById(id);
        if (materiaDetalles != null) {
            return ResponseEntity.ok(materiaDetalles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public MateriaDetalles createMateriaDetalles(@RequestBody MateriaDetalles materiaDetalles) {
        return materiaDetallesService.createMateriaDetalles(materiaDetalles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDetalles> updateMateriaDetalles(@PathVariable Long id, @RequestBody MateriaDetalles materiaDetalles) {
        MateriaDetalles updatedMateriaDetalles = materiaDetallesService.updateMateriaDetalles(id, materiaDetalles);
        if (updatedMateriaDetalles != null) {
            return ResponseEntity.ok(updatedMateriaDetalles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriaDetalles(@PathVariable Long id) {
        materiaDetallesService.deleteMateriaDetalles(id);
        return ResponseEntity.noContent().build();
    }
}
