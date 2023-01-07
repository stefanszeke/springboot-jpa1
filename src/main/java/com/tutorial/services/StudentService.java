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

    // GET all
    public List<Student> getAllStudents() {
        return studentRepository.findAll(); // This is a method from the StudentRepository, it is used to retrieve all the students from the database
    }

    // GET by id
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get(); // This is a method from the StudentRepository, it is used to retrieve a student by id from the database
    }

    // POST
    public Student createStudent(Student student) {
        return studentRepository.save(student); // This is a method from the StudentRepository, it is used to create a student in the database
    }

    // PUT, add id to request body
    public Student updateStudent(Student student) {
        return studentRepository.save(student); // This is a method from the StudentRepository, it is used to update a student in the database
    }

    // PUT by id
    public Student updateStudentById(Long id, Student student) {
        Student studentToUpdate = studentRepository.findById(id).get();

        // send error message if some fields are null:
        if (student.getFirstName() == null) { throw new RuntimeException("First name cannot be null"); }
        if (student.getLastName() == null) { throw new RuntimeException("Last name cannot be null"); }
        if (student.getEmail() == null) { throw new RuntimeException("Email cannot be null"); }

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setEmail(student.getEmail());

        return studentRepository.save(studentToUpdate);
    }

    // Patch by id
    public Student patchStudentById(Long id, Student student) {
        Student studentToUpdate = studentRepository.findById(id).get();

        if (student.getFirstName() != null) { studentToUpdate.setFirstName(student.getFirstName()); }
        if (student.getLastName() != null) { studentToUpdate.setLastName(student.getLastName()); }
        if (student.getEmail() != null) { studentToUpdate.setEmail(student.getEmail()); }

        return studentRepository.save(studentToUpdate);
    }

    // DELETE by id
    public String deleteStudentById(Long id) {
        studentRepository.deleteById(id); // This is a method from the StudentRepository, it is used to delete a student by id from the database
        return "Student with id " + id + " was deleted";
    }

}
