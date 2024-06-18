package com.asistencia.services;

import com.asistencia.models.AulaClase;
import com.asistencia.repositories.AulaClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaClaseService {

    @Autowired
    private AulaClaseRepository aulaClaseRepository;

    public List<AulaClase> findAll() {
        return aulaClaseRepository.findAll();
    }

    public AulaClase save(AulaClase aulaClase) {
        if (!isAulaAvailable(aulaClase.getClase().getId(), aulaClase.getAula().getId())) {
            throw new RuntimeException("Aula no disponible");
        }
        return aulaClaseRepository.save(aulaClase);
    }

    public void deleteById(Long id) {
        aulaClaseRepository.deleteById(id);
    }

    public boolean isAulaAvailable(Long claseId, Long aulaId) {
        List<AulaClase> existing = aulaClaseRepository.findByAulaId(aulaId);
        for (AulaClase ac : existing) {
            if (!ac.getClase().getId().equals(claseId)) {
                return false;
            }
        }
        return true;
    }
}
