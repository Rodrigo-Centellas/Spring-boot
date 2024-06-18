package com.asistencia.seeder;

import com.asistencia.models.Grupo;
import com.asistencia.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrupoSeeder implements CommandLineRunner {
    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public void run(String... args) throws Exception {
        seedGrupos();
    }

    private void seedGrupos() {
        if (grupoRepository.count() == 0) {
            Grupo grupo = new Grupo();
            grupo.setNombre("Z1");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("Z2");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("Z3");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("Z4");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("Z5");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("SA");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("SB");
            grupoRepository.save(grupo);

            grupo = new Grupo();
            grupo.setNombre("SC");
            grupoRepository.save(grupo);
        }
    }
}
