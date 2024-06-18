package com.asistencia.services;

import com.asistencia.models.Facultad;
import com.asistencia.repositories.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    public List<Facultad> getAllFacultades() {
        return facultadRepository.findAll();
    }

    public Facultad getFacultadById(Long id) {
        return facultadRepository.findById(id).orElse(null);
    }

    public Facultad createFacultad(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    public Facultad updateFacultad(Long id, Facultad facultad) {
        facultad.setId(id);
        return facultadRepository.save(facultad);
    }

    public void deleteFacultad(Long id) {
        facultadRepository.deleteById(id);
    }
}
