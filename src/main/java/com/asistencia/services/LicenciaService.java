package com.asistencia.services;

import com.asistencia.models.Licencia;
import com.asistencia.repositories.LicenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenciaService {

    @Autowired
    private LicenciaRepository licenciaRepository;

    public List<Licencia> findAll() {
        return licenciaRepository.findAll();
    }

    public Optional<Licencia> findById(Long id) {
        return licenciaRepository.findById(id);
    }

    public Licencia save(Licencia licencia) {
        return licenciaRepository.save(licencia);
    }

    public void deleteById(Long id) {
        licenciaRepository.deleteById(id);
    }
}
