package com.asistencia.controllers;

import com.asistencia.config.JwtProvider;
import com.asistencia.repositories.UserRepository;
import com.asistencia.requests.LoginRequest;
import com.asistencia.responses.AuthResponse;
import com.asistencia.services.TokenBlacklistService;
import com.asistencia.services.CustomerUserDetailsImpl;
import com.asistencia.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.asistencia.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsImpl customerUserDetailsImpl;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception {
        User isUserExist = usuarioRepository.findByEmail(user.getEmail());
        if (isUserExist != null) {
            throw new Exception("email already exists with another account");
        }

        User createdUser = new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setName(user.getName());
        createdUser.setRol(user.getRol());
        createdUser.setCi(user.getCi());
        createdUser.setDireccion(user.getDireccion());


        User savedUser = usuarioRepository.save(createdUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        String jwt= JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setMessage("signup successful");
        res.setJtw(jwt);


        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication=authenticate(username,password);
        String jwt= JwtProvider.generateToken(authentication);


        AuthResponse res = new AuthResponse();
        res.setMessage("signing successful");
        res.setJtw(jwt);
        res.setEmail(username); // agrega el email
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsImpl.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            // Aquí puedes agregar el token a una lista de tokens invalidados (blacklist)
//            // blacklistService.addTokenToBlacklist(token);
//        }
//        return ResponseEntity.ok("Logout successful");
//    }

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // Obtener la fecha de expiración del token
            LocalDateTime expirationDate = JwtProvider.getExpirationDateFromToken(token);
            // Agregar el token a la lista de tokens invalidados
            tokenBlacklistService.addTokenToBlacklist(token, expirationDate);
        }
        return ResponseEntity.ok("Logout successful");
    }
}
