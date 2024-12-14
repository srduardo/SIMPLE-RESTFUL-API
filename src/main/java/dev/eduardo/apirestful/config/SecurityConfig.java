package dev.eduardo.apirestful.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

// @EnableWebSecurity = Diz para o Spring Security não seguir pelo fluxo de configuração
// padrão, e sim seguir o fluxo de configuração especificado aqui.

public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    // Se você não especificar nenhum filtro aqui, ele por padrão não retornará nenhum.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.formLogin(Customizer.withDefaults()); = Implementa as propriedades padrões para exibir o formulário de login

        return http
                .csrf(customizer -> customizer.disable()) // Desativando a segurança contra csrf (não recomendado para sistemas em produção e sem proteções adicionais)
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated()) // Explicitando que todas as requisições precisam ser autenticadas
                .httpBasic(Customizer.withDefaults()) // Explicitando que as requisições http devem ter seu fluxo normal
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Mudando o tipo de sessão para sem estado (não mantém dados do usuário, todas as requisições são independentes)
                .build(); // Construindo o objeto
    }

    @Bean
    public AuthenticationProvider authenticationProvider() { // Alterando o autenticador padrão para um autenticador personalizado
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // Selecionando qual tipo de autenticador será usado
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Selecionando o tipo de encriptador de senha (BCryptPasswordEncoder)
        provider.setUserDetailsService(userDetailsService); // Selecionando a service que gerenciará as credenciais dos usuários
        return provider; // Retornando o provedor personalizado
    }

}
