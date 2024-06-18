package com.asistencia.services;

import com.asistencia.models.User;
import com.asistencia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        // Verificar si el ci ya existe
        if (existsByCi(user.getCi())) {
            throw new IllegalArgumentException("El CI ya está en uso.");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Lógica para obtener todos los usuarios
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        // Lógica para obtener un usuario por su ID
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        // Verificar si el ci ya existe para otro usuario
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null && !existingUser.getCi().equals(user.getCi()) && existsByCi(user.getCi())) {
            throw new IllegalArgumentException("El CI ya está en uso.");
        }
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        // Lógica para eliminar un usuario por su ID
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsByCi(String ci) {
        return userRepository.existsByCi(ci);
    }
}
