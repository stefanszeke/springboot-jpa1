package com.tutorial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.dto.StudentRequest;
import com.tutorial.entity.Student;
import com.tutorial.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

// @CrossOrigin(origins = "http://localhost:5500") // This is a cross origin request, it is used to allow requests from the client to the server
@CrossOrigin(origins = "*", allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1/students")
public class StudentController { // This is a controller, controllers are used to handle requests from the client

    @Autowired
    private StudentService studentService; // This is a dependency, it is used to handle business logic
    
    @GetMapping("") // This is a GET request, it is used to retrieve data from the server
    public List<Student> getAllStudents() {
        return studentService.getAllStudents(); // This is a method from the StudentService, it is used to retrieve all the students from the database
    }

    // get student by id on same url 
    @GetMapping("/{id}") // 
    public Student getStudentById(@PathVariable Long id) { // This is a path variable, it is used to retrieve the id from the url
        return studentService.getStudentById(id); // This is a method from the StudentService, it is used to retrieve a student by id from the database
    }

    // @PostMapping("") // This is a POST request, it is used to send data to the server
    // public Student createStudent(@RequestBody Student student) { // This is a request body, it is used to retrieve the student from the request body
    //     return studentService.createStudent(student); // This is a method from the StudentService, it is used to create a student in the database
    // }

    // POST dto
    @PostMapping("") // This is a POST request, it is used to send data to the server
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentRequest studentRequest) { 
        return new ResponseEntity<>(studentService.createStudent(studentRequest), HttpStatus.CREATED); // This is a method from the StudentService, it is used to create a student in the database
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {
    //     Map<String, String> errors = new HashMap<>();
    //     e.getBindingResult().getFieldErrors().forEach(error -> errors.put("bad request: "+error.getField(), error.getDefaultMessage()));
    //     return errors;
    // }


    // @PutMapping("/") // Update by id in request body
    // public Student updateStudent(@RequestBody Student student) {
    //     return studentService.updateStudent(student);
    // }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {

        return studentService.updateStudentById(id, student);
    }

    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.patchStudentById(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

}
