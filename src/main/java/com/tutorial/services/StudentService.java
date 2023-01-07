package com.tutorial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tutorial.entities.Student;
import com.tutorial.repositories.StudentRepository;

import java.sql.*;

@Service
public class StudentService { // This is a service, services are used to handle business logic
    
    @Autowired // This is an annotation, it tells Spring to inject the dependency, in this case the StudentRepository
    private StudentRepository studentRepository; // This is a dependency, it is used to access the database

    public List<Student> getAllStudents() {
        return studentRepository.findAll(); // This is a method from the StudentRepository, it is used to retrieve all the students from the database
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get(); // This is a method from the StudentRepository, it is used to retrieve a student by id from the database
    }

}
