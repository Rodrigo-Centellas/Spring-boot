package com.asistencia.services;

import com.asistencia.models.User;
import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
    boolean existsByCi(String ci);
}
