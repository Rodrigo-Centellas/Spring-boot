package com.asistencia.services;

import com.asistencia.models.Modulo;
import com.asistencia.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloService {
    @Autowired
    private ModuloRepository moduloRepository;

    public List<Modulo> getAllModulos() {
        return moduloRepository.findAll();
    }

    public Modulo getModuloById(Long id) {
        return moduloRepository.findById(id).orElse(null);
    }

    public Modulo saveModulo(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public void deleteModulo(Long id) {
        moduloRepository.deleteById(id);
    }
}
