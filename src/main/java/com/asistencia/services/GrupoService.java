package com.asistencia.services;

import com.asistencia.models.Grupo;
import com.asistencia.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo createGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> getGrupoById(Long id) {
        return grupoRepository.findById(id);
    }

    public Grupo updateGrupo(Long id, Grupo grupo) {
        grupo.setId(id);
        return grupoRepository.save(grupo);
    }

    public void deleteGrupo(Long id) {
        grupoRepository.deleteById(id);
    }
}
