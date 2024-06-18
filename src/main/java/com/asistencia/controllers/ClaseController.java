package com.asistencia.controllers;

import com.asistencia.models.Asistencia;
import com.asistencia.models.Clase;
import com.asistencia.models.Horario;
import com.asistencia.repositories.AsistenciaRepository;
import com.asistencia.repositories.ClaseRepository;
import com.asistencia.services.ClaseService;
import com.asistencia.services.HorarioService;
import com.asistencia.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private ClaseRepository claseRepository;
    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @GetMapping
    public List<Clase> getAllClases() {
        return claseService.getAllClases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id) {
        return claseService.getClaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Clase createClase(@RequestBody Clase clase) {
        return claseService.createClase(clase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Long id, @RequestBody Clase claseDetails) {
        return ResponseEntity.ok(claseService.updateClase(id, claseDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        claseService.deleteClase(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/generar={id}")
    public ResponseEntity<Void> generarClases(@PathVariable Long id) {
        List<Horario> horarios = horarioService.getHorariosbyClase(id);

        Optional<Clase> clase = claseService.getClaseById(id);

        Integer minutos = claseService.getMateria(id).getHoras()*60;


        LocalDate hoy = LocalDate.now();

        while (minutos > 0) {
            for (Horario horario : horarios) {
                hoy=hoy.with(TemporalAdjusters.next(DayOfWeek.valueOf(horario.getDia())));
                if (minutos>0){
                    Asistencia asistencia = new Asistencia();
                    asistencia.setFecha(hoy);
                    asistencia.setUser(clase.get().getUser());
                    asistencia.setHorario(horario);
                    asistencia.setTipo("falta");
                    asistencia.setClase(clase.get());
                    asistenciaRepository.save(asistencia);
                    minutos=minutos-((int)(Duration.between(horario.getHorarioInicio(), horario.getHorarioFin())).toMinutes());

                }
            }
        }

        return ResponseEntity.ok().build();
    }

}
