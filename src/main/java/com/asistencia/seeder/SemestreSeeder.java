package com.asistencia.seeder;

import com.asistencia.models.Semestre;
import com.asistencia.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SemestreSeeder implements CommandLineRunner {
    @Autowired
    private SemestreRepository semestreRepository;

    @Override
    public void run(String... args) throws Exception {
        seedSemestres();
    }

    private void seedSemestres() {
        if (semestreRepository.count() == 0) {
            Semestre semestre = new Semestre();
            semestre.setSemestre(1);
            semestre.setGestion(2024);
            semestreRepository.save(semestre);
//
            semestre=new Semestre();
            semestre.setSemestre(2);
            semestre.setGestion(2024);
            semestreRepository.save(semestre);

            semestre=new Semestre();
            semestre.setSemestre(3);
            semestre.setGestion(2024);
            semestreRepository.save(semestre);

            semestre=new Semestre();
            semestre.setSemestre(4);
            semestre.setGestion(2024);
            semestreRepository.save(semestre);
        }
    }
}
