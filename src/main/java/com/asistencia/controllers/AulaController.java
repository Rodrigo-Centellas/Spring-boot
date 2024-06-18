package com.asistencia.controllers;

import com.asistencia.models.Aula;
import com.asistencia.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    // Método para crear un nuevo aula
    @PostMapping
    public Aula createAula(@RequestBody Aula aula) {
        return aulaRepository.save(aula);
    }

    // Método para obtener todas las aulas
    @GetMapping
    public List<Aula> getAllAulas() {
        return aulaRepository.findAll();
    }

    // Método para obtener un aula por su ID
    @GetMapping("/{id}")
    public Optional<Aula> getAulaById(@PathVariable Long id) {
        return aulaRepository.findById(id);
    }

    // Método para actualizar un aula
    @PutMapping("/{id}")
    public Aula updateAula(@PathVariable Long id, @RequestBody Aula aula) {
        aula.setId(id); // Establecer el ID del aula a actualizar
        return aulaRepository.save(aula);
    }

    // Método para eliminar un aula por su ID
    @DeleteMapping("/{id}")
    public void deleteAula(@PathVariable Long id) {
        aulaRepository.deleteById(id);
    }
}
