package com.asistencia.controllers;

import com.asistencia.models.Asistencia;
import com.asistencia.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // Método para obtener todas las asistencias
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaService.getAllAsistencias();
    }

    // Método para obtener una asistencia por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.getAsistenciaById(id);
        return asistencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para crear una nueva asistencia
    @PostMapping
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.createAsistencia(asistencia);
        return ResponseEntity.ok(nuevaAsistencia);
    }

    // Método para actualizar una asistencia existente
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        Optional<Asistencia> asistenciaExistente = asistenciaService.getAsistenciaById(id);
        if (asistenciaExistente.isPresent()) {
            Asistencia updatedAsistencia = asistenciaService.updateAsistencia(id, asistencia);
            return ResponseEntity.ok(updatedAsistencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para eliminar una asistencia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        boolean eliminado = asistenciaService.deleteAsistencia(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
