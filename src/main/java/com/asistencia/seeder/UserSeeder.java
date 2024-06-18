package com.asistencia.seeder;

import com.asistencia.models.User;
import com.asistencia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User u = new User();
            u.setName("Jose Castro");
            u.setEmail("josehco85@gmail.com");
            u.setPassword(passwordEncoder.encode("1234"));
            u.setRol("administrador");
            u.setCi("1111111");
            u.setDireccion("Los Pozos");
            userRepository.save(u);

            u = new User();
            u.setName("Usuario1");
            u.setEmail("usuario1@gmail.com");
            u.setPassword(passwordEncoder.encode("1234"));
            u.setRol("usuario");
            u.setCi("11111111");
            u.setDireccion("Direccion usuario1");
            userRepository.save(u);

            u = new User();
            u.setName("Usuario2");
            u.setEmail("usuario2@gmail.com");
            u.setPassword(passwordEncoder.encode("1234"));
            u.setRol("usuario");
            u.setCi("11111112");
            u.setDireccion("Direccion usuario2");
            userRepository.save(u);

            u = new User();
            u.setName("Usuario3");
            u.setEmail("usuario3@gmail.com");
            u.setPassword(passwordEncoder.encode("1234"));
            u.setRol("usuario");
            u.setCi("11111113");
            u.setDireccion("Direccion usuario3");
            userRepository.save(u);

            u = new User();
            u.setName("Usuario4");
            u.setEmail("usuario4@gmail.com");
            u.setPassword(passwordEncoder.encode("1234"));
            u.setRol("usuario");
            u.setCi("11111113");
            u.setDireccion("Direccion usuario4");
            userRepository.save(u);

            u = new User();
            u.setName("Rodrigo");
            u.setEmail("rodrigo@gmail.com");
            u.setPassword(passwordEncoder.encode("123"));
            u.setRol("administrador");
            u.setCi("1111145");
            u.setDireccion("Direccion usuario4");
            userRepository.save(u);
        }
    }
}
