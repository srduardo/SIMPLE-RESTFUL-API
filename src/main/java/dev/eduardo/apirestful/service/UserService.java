package dev.eduardo.apirestful.service;

import dev.eduardo.apirestful.dto.UserDto;
import dev.eduardo.apirestful.model.User;
import dev.eduardo.apirestful.repository.UserRepository;
import dev.eduardo.apirestful.service.security.JwtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoderPassword = new BCryptPasswordEncoder(12); // Instancia o encriptador

    public List<UserDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(), user.getPassword(), user.getBio()))
                .toList();
    }

    public UserDto findUserById(Integer id) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        return new UserDto(user.get().getName(), user.get().getEmail(), user.get().getPassword(), user.get().getBio());
    }

    public void createUser(UserDto userDto) throws RuntimeException{
        if (userDto == null) {
            throw new RuntimeException("Sem dados o suficiente.");
        }

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(encoderPassword.encode(user.getPassword())); // Define a senha encriptada no usuário
        userRepository.save(user);
    }

    public void deleteUserById(Integer id) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        userRepository.delete(user.get());
    }

    public UserDto updateUserById(Integer id, UserDto userDto) throws RuntimeException {
        Optional<User> oldUser = userRepository.findById(id);

        if (oldUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User updatedUser = oldUser.get();
        BeanUtils.copyProperties(userDto, updatedUser);
        updatedUser.setPassword(encoderPassword.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);

        return userDto;
    }

    public String verify(UserDto userDto) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDto.getEmail());
        }

        return "Fail...";
    }
}
