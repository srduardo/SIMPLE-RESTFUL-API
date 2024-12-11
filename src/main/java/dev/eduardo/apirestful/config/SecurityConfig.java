package dev.eduardo.apirestful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

// @EnableWebSecurity = Diz para o Spring Security não seguir pelo fluxo de configuração
// padrão, e sim seguir o fluxo de configuração especificado aqui.

public class SecurityConfig {

// Se você não especificar nenhum filtro aqui, ele por padrão não retornará nenhum.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.formLogin(Customizer.withDefaults()); = Implementa as propriedades padrões para exibir o formulário de login

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
