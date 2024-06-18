// src/main/java/com/asistencia/services/MateriaService.java
package com.asistencia.services;

import com.asistencia.models.Materia;
import com.asistencia.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    public Materia getMateriaById(Long id) {
        return materiaRepository.findById(id).orElse(null);
    }

    public Materia createMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    public Materia updateMateria(Long id, Materia materia) {
        Materia existingMateria = materiaRepository.findById(id).orElse(null);
        if (existingMateria != null) {
            existingMateria.setNombre(materia.getNombre());
            existingMateria.setSigla(materia.getSigla());
            existingMateria.setHoras(materia.getHoras());
            existingMateria.setNivel(materia.getNivel());
            return materiaRepository.save(existingMateria);
        }
        return null;
    }

    public void deleteMateria(Long id) {
        materiaRepository.deleteById(id);
    }
}
