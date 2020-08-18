package com.studentapi.controllers;

import com.studentapi.exceptions.ResourceNotFoundException;
import com.studentapi.models.Course;
import com.studentapi.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/")
public class CourseController {

    @Autowired
    private ICourseRepository courseRepository;

    @GetMapping("courses")
    public List<Course> getAllCourses(){
        return this.courseRepository.findAll();
    }

    @GetMapping("courses/{id}")
    public Course getCourseById(@PathVariable(value = "id") long courseId){
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course was not found with id " + courseId));
    }

    @PostMapping("courses")
    public Course createCourse(@RequestBody Course course){
        return this.courseRepository.save(course);
    }
}
