package com.asistencia.controllers;

import com.asistencia.models.Modulo;
import com.asistencia.services.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/modulos")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;

    @GetMapping
    public List<Modulo> getAllModulos() {
        return moduloService.getAllModulos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> getModuloById(@PathVariable Long id) {
        Modulo modulo = moduloService.getModuloById(id);
        return modulo != null ? ResponseEntity.ok(modulo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Modulo createModulo(@RequestBody Modulo modulo) {
        return moduloService.saveModulo(modulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modulo> updateModulo(@PathVariable Long id, @RequestBody Modulo modulo) {
        Modulo existingModulo = moduloService.getModuloById(id);
        if (existingModulo != null) {
            modulo.setId(id);
            return ResponseEntity.ok(moduloService.saveModulo(modulo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModulo(@PathVariable Long id) {
        Modulo existingModulo = moduloService.getModuloById(id);
        if (existingModulo != null) {
            moduloService.deleteModulo(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
