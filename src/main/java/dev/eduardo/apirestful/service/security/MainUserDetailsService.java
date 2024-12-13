package dev.eduardo.apirestful.service.security;

import dev.eduardo.apirestful.model.User;
import dev.eduardo.apirestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("Usuário não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new MainUserDetails(user);
    }

}
