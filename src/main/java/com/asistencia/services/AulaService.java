package com.asistencia.services;

import com.asistencia.models.Aula;
import com.asistencia.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {
    private final AulaRepository aulaRepository;

    @Autowired
    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<Aula> getAllAulas() {
        return aulaRepository.findAll();
    }

    public Optional<Aula> getAulaById(Long id) {
        return aulaRepository.findById(id);
    }

    public Aula createAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public Aula updateAula(Long id, Aula aulaDetails) {
        Optional<Aula> aulaOptional = aulaRepository.findById(id);
        if (aulaOptional.isPresent()) {
            Aula aula = aulaOptional.get();
            aula.setNombre(aulaDetails.getNombre());
            aula.setCapacidad(aulaDetails.getCapacidad());
            return aulaRepository.save(aula);
        } else {
            // Manejar el caso en el que el aula no existe
            return null;
        }
    }

    public void deleteAula(Long id) {
        aulaRepository.deleteById(id);
    }

}
