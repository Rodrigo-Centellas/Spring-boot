package com.asistencia.services;

import com.asistencia.models.Asistencia;
import com.asistencia.repositories.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id);
    }

    public Asistencia createAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public Asistencia updateAsistencia(Long id, Asistencia asistencia) {
        asistencia.setId(id);
        return asistenciaRepository.save(asistencia);
    }

    public boolean deleteAsistencia(Long id) {
        if (asistenciaRepository.existsById(id)) {
            asistenciaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
