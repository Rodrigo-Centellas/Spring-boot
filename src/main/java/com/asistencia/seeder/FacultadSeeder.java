package com.asistencia.seeder;

import com.asistencia.models.Facultad;
import com.asistencia.models.User;
import com.asistencia.repositories.FacultadRepository;
import com.asistencia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FacultadSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedFacultades();
    }

    private void seedFacultades() {
        if (facultadRepository.count() == 0) {
            Facultad facultad1 = new Facultad();
            facultad1.setNombre("Facultad de Medicina");


            Facultad facultad2 = new Facultad();
            facultad2.setNombre("Facultad de Ciencias de la computacion");

            facultadRepository.save(facultad1);
            facultadRepository.save(facultad2);
        }
    }
}
