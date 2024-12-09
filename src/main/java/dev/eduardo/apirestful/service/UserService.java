package dev.eduardo.apirestful.service;

import dev.eduardo.apirestful.dto.UserDTO;
import dev.eduardo.apirestful.model.User;
import dev.eduardo.apirestful.repository.UserRepository;
import org.antlr.v4.runtime.InputMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> new UserDTO(user.getName(), user.getEmail(), user.getBio()))
                .toList();
    }

    public UserDTO findUserById(Integer id) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        return new UserDTO(user.get().getName(), user.get().getEmail(), user.get().getBio());
    }

    public void createUser(UserDTO userDTO) throws RuntimeException {
        if (userDTO == null) {
            throw new RuntimeException("Nome, e-mail e bio necessários.");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBio(userDTO.getBio());

        userRepository.save(user);
    }

    public void deleteUserById(Integer id) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        userRepository.delete(user.get());
    }

    public UserDTO updateUserById(Integer id, UserDTO userDTO) throws RuntimeException {
        Optional<User> oldUser = userRepository.findById(id);

        if (oldUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User updatedUser = oldUser.get();
        updatedUser.setName(userDTO.getName());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setBio(userDTO.getBio());

        userRepository.save(updatedUser);

        return new UserDTO(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getBio());
    }
}
