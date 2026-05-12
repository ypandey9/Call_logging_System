package com.ypandey.calllogsystem.config;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

    return http
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/h2-console/**")
                    .disable()
            )

            .headers(headers ->
                    headers.frameOptions(frame -> frame.disable())
            )

            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            )

            .httpBasic(Customizer.withDefaults())

            .build();
}
}


