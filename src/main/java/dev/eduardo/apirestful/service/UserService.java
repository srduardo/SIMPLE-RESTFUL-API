package dev.eduardo.apirestful.service;

import dev.eduardo.apirestful.dto.UserDto;
import dev.eduardo.apirestful.model.User;
import dev.eduardo.apirestful.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(), user.getBio()))
                .toList();
    }

    public UserDto findUserById(Integer id) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        return new UserDto(user.get().getName(), user.get().getEmail(), user.get().getBio());
    }

    public void createUser(UserDto userDto) throws RuntimeException{
        if (userDto.getName().isEmpty() || userDto.getEmail().isEmpty() || userDto.getBio().isEmpty()) {
            throw new RuntimeException("Nome, e-mail e bio necessários.");
        }

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword("Null");

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
        userRepository.save(updatedUser);

        return userDto;
    }
}
