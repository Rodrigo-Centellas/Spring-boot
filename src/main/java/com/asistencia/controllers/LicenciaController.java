package com.asistencia.controllers;

import com.asistencia.models.Licencia;
import com.asistencia.models.User;
import com.asistencia.repositories.LicenciaRepository;
import com.asistencia.repositories.UserRepository;
import com.asistencia.services.LicenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licencias")
public class LicenciaController {

    @Autowired
    private LicenciaRepository licenciaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Licencia> getAllLicencias() {
        return licenciaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Licencia> getLicenciaById(@PathVariable Long id) {
        return licenciaRepository.findById(id)
                .map(licencia -> ResponseEntity.ok().body(licencia))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Licencia> createLicencia(@RequestBody Licencia licencia) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // Obtener el email del usuario autenticado
        User user = userRepository.findByEmail(email);  // Encontrar el usuario por email

        if (user != null) {
            licencia.setUser(user);  // Asignar el usuario a la licencia
            Licencia nuevaLicencia = licenciaRepository.save(licencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaLicencia);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Licencia> updateLicencia(@PathVariable Long id, @RequestBody Licencia licenciaDetalles) {
        return licenciaRepository.findById(id)
                .map(licencia -> {
                    licencia.setMotivo(licenciaDetalles.getMotivo());
                    licencia.setDescripcion(licenciaDetalles.getDescripcion());
                    licencia.setInicio(licenciaDetalles.getInicio());
                    licencia.setFin(licenciaDetalles.getFin());
                    licencia.setEstado(licenciaDetalles.getEstado());
                    Licencia updatedLicencia = licenciaRepository.save(licencia);
                    return ResponseEntity.ok().body(updatedLicencia);
                }).orElse(ResponseEntity.notFound().build());
    }
//    @GetMapping("/user")
//    public List<Licencia> getLicenciasPorUsuario(Authentication authentication) {
//        User usuario = (User) authentication.getPrincipal();
//        return licenciaRepository.findByUser(usuario);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLicencia(@PathVariable Long id) {
//        return licenciaRepository.findById(id)
//                .map(licencia -> {
//                    licenciaRepository.delete(licencia);
//                    return ResponseEntity.noContent().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
//        LicenciaService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
