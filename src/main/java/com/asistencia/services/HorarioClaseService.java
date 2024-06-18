//package com.asistencia.services;
//
//import com.asistencia.models.HorarioClase;
//import com.asistencia.repositories.HorarioClaseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class HorarioClaseService {
//
//    @Autowired
//    private HorarioClaseRepository horarioClaseRepository;
//
//    public List<HorarioClase> findAll() {
//        return horarioClaseRepository.findAll();
//    }
//
//    public HorarioClase save(HorarioClase horarioClase) {
//        if (!isHorarioAvailable(horarioClase.getClase().getId(), horarioClase.getHorario().getId())) {
//            throw new RuntimeException("Horario no disponible");
//        }
//        return horarioClaseRepository.save(horarioClase);
//    }
//
//    public void deleteById(Long id) {
//        horarioClaseRepository.deleteById(id);
//    }
//
//    public boolean isHorarioAvailable(Long claseId, Long horarioId) {
//        List<HorarioClase> existing = horarioClaseRepository.findByHorarioId(horarioId);
//        for (HorarioClase hc : existing) {
//            if (!hc.getClase().getId().equals(claseId)) {
//                return false;
//            }
//        }
//        return true;
//    }
//}

package com.asistencia.services;

import com.asistencia.models.HorarioClase;
import com.asistencia.repositories.HorarioClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class HorarioClaseService {

    @Autowired
    private HorarioClaseRepository horarioClaseRepository;

    public List<HorarioClase> findAll() {
        return horarioClaseRepository.findAll();
    }

    public HorarioClase save(HorarioClase newHorarioClase) {
        Long userId = newHorarioClase.getClase().getUser().getId();
        Long claseId = newHorarioClase.getClase().getId();
        String dia = newHorarioClase.getHorario().getDia();
        LocalTime horarioInicio = newHorarioClase.getHorario().getHorarioInicio();
        LocalTime horarioFin = newHorarioClase.getHorario().getHorarioFin();

        List<HorarioClase> existingHorariosClase = horarioClaseRepository.findByClaseUserId(userId);

        for (HorarioClase existingHorarioClase : existingHorariosClase) {
            if (existingHorarioClase.getClase().getId().equals(claseId)) {
                // Ignorar la misma clase (actualizaciones permitidas)
                continue;
            }

            if (existingHorarioClase.getHorario().getDia().equals(dia)) {
                LocalTime existingStart = existingHorarioClase.getHorario().getHorarioInicio();
                LocalTime existingEnd = existingHorarioClase.getHorario().getHorarioFin();

                boolean isOverlapping = horarioInicio.isBefore(existingEnd) && horarioFin.isAfter(existingStart);

                if (isOverlapping) {
                    throw new IllegalArgumentException("Horario superpuesto para el usuario " + userId + " en el día " + dia);
                }
            }
        }

        return horarioClaseRepository.save(newHorarioClase);
    }

    public void deleteById(Long id) {
        horarioClaseRepository.deleteById(id);
    }

    private boolean isHorarioAvailable(HorarioClase newHorarioClase) {
        Long userId = newHorarioClase.getClase().getUser().getId();
        Long newClaseId = newHorarioClase.getClase().getId();
        List<HorarioClase> existingHorariosClase = horarioClaseRepository.findByClaseUserId(userId);

        for (HorarioClase existingHorarioClase : existingHorariosClase) {
            if (newHorarioClase.getId() != null && existingHorarioClase.getId().equals(newHorarioClase.getId())) {
                // Ignorar la misma instancia de HorarioClase que se está actualizando
                continue;
            }

            if (existingHorarioClase.getHorario().getDia().equals(newHorarioClase.getHorario().getDia()) &&
                    !existingHorarioClase.getClase().getId().equals(newClaseId)) {
                LocalTime newStart = newHorarioClase.getHorario().getHorarioInicio();
                LocalTime newEnd = newHorarioClase.getHorario().getHorarioFin();
                LocalTime existingStart = existingHorarioClase.getHorario().getHorarioInicio();
                LocalTime existingEnd = existingHorarioClase.getHorario().getHorarioFin();

                boolean isOverlapping = newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);

                if (isOverlapping) {
                    return false;
                }
            }
        }
        return true;
    }
}

