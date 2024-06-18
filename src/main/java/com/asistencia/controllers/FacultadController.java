package com.asistencia.controllers;

import com.asistencia.models.Facultad;
import com.asistencia.services.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultades")
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    @GetMapping
    public List<Facultad> getAllFacultades() {
        return facultadService.getAllFacultades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable Long id) {
        Facultad facultad = facultadService.getFacultadById(id);
        if (facultad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultad);
    }

    @PostMapping
    public Facultad createFacultad(@RequestBody Facultad facultad) {
        return facultadService.createFacultad(facultad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable Long id, @RequestBody Facultad facultad) {
        Facultad updatedFacultad = facultadService.updateFacultad(id, facultad);
        if (updatedFacultad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFacultad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable Long id) {
        facultadService.deleteFacultad(id);
        return ResponseEntity.noContent().build();
    }
}
