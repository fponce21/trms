package com.francisco.trms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.francisco.trms.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    // Constructor-based DI
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Updated for Spring Security 6.1+
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> 
                authz
                    .requestMatchers("/login", "/register").permitAll() // Public pages
                    .anyRequest().authenticated() // Other pages need authentication
            )
            .formLogin(form -> 
            form
                .loginPage("/login")  // Custom login page
                .loginProcessingUrl("/login") // Specify the login processing URL
                .usernameParameter("username") // Optional: Custom username field
                .passwordParameter("password") // Optional: Custom password field
                .permitAll()
        )
        .logout(logout -> 
            logout.permitAll()
        );
    
    return http.build();
}
}
