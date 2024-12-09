package dev.eduardo.apirestful.controller;

import dev.eduardo.apirestful.dto.UserDTO;
import dev.eduardo.apirestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = userService.findAllUsers();
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO userDTO;

        try {
            userDTO = userService.findUserById(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(406).build();
        }

        return ResponseEntity.status(201).body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDto;

        try {
            updatedUserDto = userService.updateUserById(id, userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Integer id) {
        try {
            userService.deleteUserById(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

        return ResponseEntity.status(204).build();
    }
}
