// src/main/java/com/asistencia/controllers/MateriaController.java
package com.asistencia.controllers;

import com.asistencia.models.Materia;
import com.asistencia.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Long id) {
        Materia materia = materiaService.getMateriaById(id);
        if (materia != null) {
            return ResponseEntity.ok(materia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.createMateria(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable Long id, @RequestBody Materia materia) {
        Materia updatedMateria = materiaService.updateMateria(id, materia);
        if (updatedMateria != null) {
            return ResponseEntity.ok(updatedMateria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
        return ResponseEntity.noContent().build();
    }
}
