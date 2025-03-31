package com.LeMart.security;

import com.LeMart.security.JwtAuthenticationEntryPoint;
import com.LeMart.security.JwtAuthenticationFilter;
import com.LeMart.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()  // Explicitly enable CORS
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/search").permitAll()
                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                //.requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                //.requestMatchers(HttpMethod.PUT, "/users/**").permitAll()
                // Changed cart endpoints to require authentication
                .requestMatchers(HttpMethod.GET, "/carts/user/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/carts/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/carts/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/carts/user/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/carts/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/users/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/users/**").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/users/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/orders/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/orders/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/orders/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/orders/**").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/orders/**").authenticated()
                // Changed admin checks to use hasAuthority() instead of hasRole()
                //.requestMatchers("/users/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/products/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/categories/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}