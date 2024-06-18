package com.asistencia.services;

import com.asistencia.models.Clase;
import com.asistencia.models.Horario;
import com.asistencia.models.HorarioClase;
import com.asistencia.repositories.HorarioClaseRepository;
import com.asistencia.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private HorarioClaseRepository horarioClaseRepository;

    public List<Horario>getHorariosbyClase(@PathVariable Long id){
        List<Horario> a= new ArrayList<>();
        List<HorarioClase> horarioClases= horarioClaseRepository.findAll(); //horarioClaseRepository.findAll();
        for(HorarioClase horarioClase:horarioClases){
            if(horarioClase.getClase().getId()==id){
                a.add(horarioClase.getHorario());
            }
        }
        return a;
    }

    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    public Horario updateHorario(Long id, Horario horario) {
        horario.setId(id);
        return horarioRepository.save(horario);
    }

    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
