package com.francisco.trms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBCryptHash {
    public static void main(String[] args) {
        // Create BCryptPasswordEncoder instance
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        
        // Password to hash
        String password = "testpass";
        
        // Generate and print the hash
        String hash = encoder.encode(password);
        System.out.println("BCrypt hash for '" + password + "': " + hash);
    }
}
