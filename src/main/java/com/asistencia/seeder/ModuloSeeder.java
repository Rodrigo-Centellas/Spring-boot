package com.asistencia.seeder;

import com.asistencia.models.Aula;
import com.asistencia.models.Horario;
import com.asistencia.models.Modulo;
import com.asistencia.repositories.AulaRepository;
import com.asistencia.repositories.HorarioRepository;
import com.asistencia.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ModuloSeeder implements CommandLineRunner {
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private AulaRepository aulaRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public void run(String... args) throws Exception {
        seedModulos();
    }

    private void seedModulos() {
        if (moduloRepository.count() == 0) {
            Modulo modulo1 = new Modulo();
            modulo1.setNombre("Modulo 236");
            moduloRepository.save(modulo1);

            Modulo modulo2 = new Modulo();
            modulo2.setNombre("Modulo 237");
            moduloRepository.save(modulo2);

            Modulo modulo3 = new Modulo();
            modulo3.setNombre("Modulo 238");
            moduloRepository.save(modulo3);

            Modulo modulo4 = new Modulo();
            modulo4.setNombre("Modulo 239");
            moduloRepository.save(modulo4);

            if (aulaRepository.count() == 0) {
                Aula aula = new Aula();
                aula.setNombre("10");
                aula.setCapacidad(40);
                aula.setModulo(modulo1);
                aulaRepository.save(aula);

                Aula aula1 = new Aula();
                aula1.setNombre("11");
                aula1.setCapacidad(45);
                aula1.setModulo(modulo2);
                aulaRepository.save(aula1);

                Aula aula2 = new Aula();
                aula2.setNombre("12");
                aula2.setCapacidad(30);
                aula2.setModulo(modulo3);
                aulaRepository.save(aula2);

                Aula aula3 = new Aula();
                aula3.setNombre("13");
                aula3.setCapacidad(60);
                aula3.setModulo(modulo4);
                aulaRepository.save(aula3);

                if (horarioRepository.count() == 0) {
                    Horario horario = new Horario();
                    horario.setDia("MONDAY");
                    horario.setHorarioInicio(LocalTime.parse("07:00"));
                    horario.setHorarioFin(LocalTime.parse("08:30"));
                    horario.setAula(aula);
                    horarioRepository.save(horario);

                    horario = new Horario();
                    horario.setDia("TUESDAY");
                    horario.setHorarioInicio(LocalTime.parse("07:00"));
                    horario.setHorarioFin(LocalTime.parse("09:15"));
                    horario.setAula(aula);
                    horarioRepository.save(horario);

                    horario = new Horario();
                    horario.setDia("WEDNESDAY");
                    horario.setHorarioInicio(LocalTime.parse("08:30"));
                    horario.setHorarioFin(LocalTime.parse("10:00"));
                    horario.setAula(aula1);
                    horarioRepository.save(horario);

                    horario = new Horario();
                    horario.setDia("THURSDAY");
                    horario.setHorarioInicio(LocalTime.parse("09:15"));
                    horario.setHorarioFin(LocalTime.parse("11:30"));
                    horario.setAula(aula2);
                    horarioRepository.save(horario);

                    horario = new Horario();
                    horario.setDia("FRIDAY");
                    horario.setHorarioInicio(LocalTime.parse("10:00"));
                    horario.setHorarioFin(LocalTime.parse("11:30"));
                    horario.setAula(aula3);
                    horarioRepository.save(horario);
                }
            }
        }
    }
}
