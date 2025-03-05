package dev.starzynski.artgen_backend.Service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class GenerateRandomStringService {
    public String generateRandom(Integer l) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(l);

        for (int i = 0; i < l; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
