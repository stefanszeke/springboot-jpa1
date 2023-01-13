package com.tutorial.dto;

import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// DTO = Data Transfer Object, it is used to transfer data between processes, it is not an entity, it is not a table in the database.
@Data 
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor 
public class StudentRequest {

    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @NotNull(message = "First name is required")
    private String firstName;


    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @NotNull(message = "Last name is required")
    private String lastName;

    // @Email(message = "Please enter a valid email address")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Please enter a valid email address")
    private String email;
}
