package com.studentapi.controllers;

import com.studentapi.exceptions.ResourceNotFoundException;
import com.studentapi.models.Student;
import com.studentapi.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/")
public class StudentController {

    @Autowired
    private IStudentRepository studentRepository;

    @GetMapping("students")
    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable(value = "id") long studentId){
        return this.studentRepository.findById(studentId)
                .orElseThrow(() -> new   ResourceNotFoundException("Student was not found with id " + studentId));
    }

    @PostMapping("students")
    public Student createStudent(@RequestBody Student student){
        return this.studentRepository.save(student);
    }

}
