package dev.eduardo.apirestful.config;

import dev.eduardo.apirestful.service.security.JwtService;
import dev.eduardo.apirestful.service.security.MainUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // Pega o cabeçalho de "Authorization" da requisição.

        // Define as variáveis que receberão o token e o email
        String token = null;
        String email = null;

        // Verifica se o cabeçalho de autenticação não está vazio e se ele começa  com "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extrai o token do cabeçalho "Authorization"
            email = jwtService.extractEmail(token); // Extrai o email do token
        }

        // Verifica se o email foi obtido e se existe alguma autenticação no contexto de segurança atual
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Pega os detalhes do usuário através do email
            UserDetails userDetails = applicationContext.getBean(MainUserDetailsService.class).loadUserByUsername(email);

            // Verifica se o token é válido em relação aos dados do usuário
            if (jwtService.validateToken(token, userDetails)) {

                // Cria um token autenticado sem a necessidade da senha pois o token é válido
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Adiciona outros detalhes no token autenticado (como IP, por exemplo)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Adiciona o token autenticado no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Passa a requisição para o próximo filtro do filterChain ou para o final da operação
        filterChain.doFilter(request, response);
    }
}
