package com.asistencia.controllers;

import com.asistencia.models.Grupo;
import com.asistencia.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    // Método para crear un nuevo grupo
    @PostMapping
    public Grupo createGrupo(@RequestBody Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    // Método para obtener todos los grupos
    @GetMapping
    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }

    // Método para obtener un grupo por su ID
    @GetMapping("/{id}")
    public Optional<Grupo> getGrupoById(@PathVariable Long id) {
        return grupoRepository.findById(id);
    }

    // Método para actualizar un grupo
    @PutMapping("/{id}")
    public Grupo updateGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        grupo.setId(id); // Establecer el ID del grupo a actualizar
        return grupoRepository.save(grupo);
    }

    // Método para eliminar un grupo por su ID
    @DeleteMapping("/{id}")
    public void deleteGrupo(@PathVariable Long id) {
        grupoRepository.deleteById(id);
    }
}
