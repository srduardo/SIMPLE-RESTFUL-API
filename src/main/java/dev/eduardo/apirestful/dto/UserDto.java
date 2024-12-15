package dev.eduardo.apirestful.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(max = 50)
    private String name;
    @NotBlank
    @Email
    @Size(max = 320)
    private String email;
    @NotBlank
    @Size(max = 100)
    private String password;
    @NotBlank
    private String bio;

    public UserDto(String name, String email, String password, String bio) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
    }

    public @NotBlank @Size(max = 50) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 50) String name) {
        this.name = name;
    }

    public @NotBlank @Email @Size(max = 320) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email @Size(max = 320) String email) {
        this.email = email;
    }

    public @NotBlank @Size(max = 100) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(max = 100) String password) {
        this.password = password;
    }

    public @NotBlank String getBio() {
        return bio;
    }

    public void setBio(@NotBlank String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
