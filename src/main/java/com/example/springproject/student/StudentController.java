package com.example.springproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Api layer
@RestController
@RequestMapping(path="/api")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students")
    public List<Student> getStudents() {
        return StudentService.getStudents();

    }
    @PostMapping(path = "/students")
    public String registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);

        return "Details added successfully";

    }
    @DeleteMapping(path = "/students/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);

    }

    @PutMapping(path = "{studentId}")
    public String updateStudent(
            @PathVariable Long studentId, @RequestBody Student student) {
        studentService.updateStudent(studentId,student);

        return "Details updated successfully";

    }




}
