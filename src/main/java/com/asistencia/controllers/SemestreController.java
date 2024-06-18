package com.asistencia.controllers;

import com.asistencia.models.Semestre;
import com.asistencia.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/semestres")
public class SemestreController {

    @Autowired
    private SemestreRepository semestreRepository;

    // Método para crear un nuevo semestre
    @PostMapping
    public Semestre createSemestre(@RequestBody Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    // Método para obtener todos los semestres
    @GetMapping
    public List<Semestre> getAllSemestres() {
        return semestreRepository.findAll();
    }

    // Método para obtener un semestre por su ID
    @GetMapping("/{id}")
    public Optional<Semestre> getSemestreById(@PathVariable Long id) {
        return semestreRepository.findById(id);
    }

    // Método para actualizar un semestre
    @PutMapping("/{id}")
    public Semestre updateSemestre(@PathVariable Long id, @RequestBody Semestre semestre) {
        semestre.setId(id); // Establecer el ID del semestre a actualizar
        return semestreRepository.save(semestre);
    }

    // Método para eliminar un semestre por su ID
    @DeleteMapping("/{id}")
    public void deleteSemestre(@PathVariable Long id) {
        semestreRepository.deleteById(id);
    }
}
