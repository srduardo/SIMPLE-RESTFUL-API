package dev.eduardo.apirestful.controller;

import dev.eduardo.apirestful.dto.UserDto;
import dev.eduardo.apirestful.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersDTO = userService.findAllUsers();
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto;

        try {
            userDto = userService.findUserById(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(406).build();
        }

        return ResponseEntity.status(201).body(userDto);
    }

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody UserDto userDto) {
        return userService.verify(userDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> putUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUserDto;

        try {
            updatedUserDto = userService.updateUserById(id, userDto);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Integer id) {
        try {
            userService.deleteUserById(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

        return ResponseEntity.status(204).build();
    }
}
