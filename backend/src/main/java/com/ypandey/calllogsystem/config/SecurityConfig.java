package com.ypandey.calllogsystem.config;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ypandey.calllogsystem.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtFilter;

        public SecurityConfig(
                JwtAuthenticationFilter jwtFilter
        ) {
        this.jwtFilter = jwtFilter;
        }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config
) throws Exception {

    return config.getAuthenticationManager();
}

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     return http
    //             .csrf(csrf -> csrf.disable())
    //             .authorizeHttpRequests(auth -> auth
    //                     .requestMatchers("/api/calls/**").authenticated()
    //                     .anyRequest().permitAll()
    //             )
    //             .httpBasic(Customizer.withDefaults()) // simple login
    //             .build();
    // }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

    http
        .csrf(csrf -> csrf.disable())

        .headers(headers ->
            headers.frameOptions(frame -> frame.sameOrigin())
        )

        .sessionManagement(session ->
            session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
            )
        )

        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                    "/auth/**",
                    "/h2-console/**"
            ).permitAll()

            .anyRequest().authenticated()
        )

        .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

    return http.build();
}

}


