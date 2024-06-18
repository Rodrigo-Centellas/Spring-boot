package com.asistencia.controllers;

import com.asistencia.models.Horario;
import com.asistencia.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // Endpoint para crear un nuevo horario
    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        Horario nuevoHorario = horarioService.createHorario(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los horarios
    @GetMapping
    public ResponseEntity<List<Horario>> getAllHorarios() {
        List<Horario> horarios = horarioService.getAllHorarios();
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    // Endpoint para obtener un horario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.getHorarioById(id);
        return horario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un horario
    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        Horario updatedHorario = horarioService.updateHorario(id, horario);
        return new ResponseEntity<>(updatedHorario, HttpStatus.OK);
    }

    // Endpoint para eliminar un horario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
