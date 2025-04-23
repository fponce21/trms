package com.francisco.trms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.francisco.trms.models.Users;
import com.francisco.trms.repositories.UserRepository;

@Service
public class UserService {
	    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	    private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public Users createUser(Users user) {
	        logger.info("Creating user: {}", user.getUsername());
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        Users savedUser = userRepository.save(user);
	        logger.debug("User created: {}", savedUser.getUsername());
	        return savedUser;
	    }
	}
