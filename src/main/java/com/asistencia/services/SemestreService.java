package com.asistencia.services;

import com.asistencia.models.Semestre;
import com.asistencia.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    public Semestre createSemestre(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    public List<Semestre> getAllSemestres() {
        return semestreRepository.findAll();
    }

    public Optional<Semestre> getSemestreById(Long id) {
        return semestreRepository.findById(id);
    }

    public Semestre updateSemestre(Long id, Semestre semestre) {
        semestre.setId(id);
        return semestreRepository.save(semestre);
    }

    public void deleteSemestre(Long id) {
        semestreRepository.deleteById(id);
    }
}
