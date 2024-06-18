package com.asistencia.controllers;

import com.asistencia.models.Carrera;
import com.asistencia.models.Facultad;
import com.asistencia.repositories.CarreraRepository;
import com.asistencia.repositories.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Carrera> createCarrera(@RequestBody Carrera carrera) {
        Optional<Facultad> facultadOpt = facultadRepository.findById(carrera.getFacultad().getId());
        if (!facultadOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carrera.setFacultad(facultadOpt.get());
        Carrera savedCarrera = carreraRepository.save(carrera);
        return new ResponseEntity<>(savedCarrera, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable Long id, @RequestBody Carrera carrera) {
        if (!carreraRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Facultad> facultadOpt = facultadRepository.findById(carrera.getFacultad().getId());
        if (!facultadOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carrera.setFacultad(facultadOpt.get());
        carrera.setId(id);
        Carrera updatedCarrera = carreraRepository.save(carrera);
        return new ResponseEntity<>(updatedCarrera, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
        if (!carreraRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carreraRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
