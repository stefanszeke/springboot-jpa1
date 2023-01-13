package com.tutorial.entities;

import javax.validation.constraints.Size;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "students")
@Data // This is an annotation, it tells Lombok to generate getters and setters
@AllArgsConstructor // This is an annotation, it tells Lombok to generate a constructor with all the fields
@NoArgsConstructor // This is an annotation, it tells Lombok to generate a default constructor
public class Student {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 
    private Long id;

    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @Column(name = "email") // This is optional, because the column name is the same as the field name
    @Email(message = "Please enter a valid email address")
    private String email;

    // public Student() {} // This is a default constructor, it is required by Hibernate

    // public Student(Long id, String firstName, String lastName, String email) {
    //     this.id = id;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.email = email;
    // }

    // getters and setters:
    // public Long getId() { return id;}
    // public void setId(Long id) { this.id = id; }
    // public String getFirstName() { return firstName; }
    // public void setFirstName(String firstName) { this.firstName = firstName; }
    // public String getLastName() { return lastName; }
    // public void setLastName(String lastName) { this.lastName = lastName; }
    // public String getEmail() { return email; }
    // public void setEmail(String email) { this.email = email; }
}
