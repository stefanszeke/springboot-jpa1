package com.tutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.entities.Student;


public interface StudentRepository extends JpaRepository<Student, Long> { // student is the entity, Long is the type of the primary key
    
}
