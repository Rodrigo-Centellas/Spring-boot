package com.asistencia.services;

import com.asistencia.models.TokenBlacklist;
import com.asistencia.repositories.TokenBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenBlacklistService {

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    public void addTokenToBlacklist(String token, LocalDateTime expirationDate) {
        TokenBlacklist tokenBlacklist = new TokenBlacklist(null, token, expirationDate);
        tokenBlacklistRepository.save(tokenBlacklist);
    }

    public boolean isTokenBlacklisted(String token) {
        Optional<TokenBlacklist> tokenBlacklistOpt = tokenBlacklistRepository.findByToken(token);
        return tokenBlacklistOpt.isPresent();
    }
}