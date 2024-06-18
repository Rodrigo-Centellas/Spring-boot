// src/main/java/com/asistencia/services/MateriaDetallesService.java
package com.asistencia.services;

import com.asistencia.models.MateriaDetalles;
import com.asistencia.repositories.MateriaDetallesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaDetallesService {

    @Autowired
    private MateriaDetallesRepository materiaDetallesRepository;

    public List<MateriaDetalles> getAllMateriaDetalles() {
        return materiaDetallesRepository.findAll();
    }

    public MateriaDetalles getMateriaDetallesById(Long id) {
        return materiaDetallesRepository.findById(id).orElse(null);
    }

    public MateriaDetalles createMateriaDetalles(MateriaDetalles materiaDetalles) {
        return materiaDetallesRepository.save(materiaDetalles);
    }

    public MateriaDetalles updateMateriaDetalles(Long id, MateriaDetalles materiaDetalles) {
        MateriaDetalles existingMateriaDetalles = materiaDetallesRepository.findById(id).orElse(null);
        if (existingMateriaDetalles != null) {
            existingMateriaDetalles.setCarrera(materiaDetalles.getCarrera());
            existingMateriaDetalles.setMateria(materiaDetalles.getMateria());
            return materiaDetallesRepository.save(existingMateriaDetalles);
        }
        return null;
    }

    public void deleteMateriaDetalles(Long id) {
        materiaDetallesRepository.deleteById(id);
    }
}
