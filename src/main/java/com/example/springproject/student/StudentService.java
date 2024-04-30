package com.example.springproject.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


// service layer
@Service
public class StudentService {

    private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public static List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public static void addNewStudent(Student student) {
        //System.out.println(student);

        Optional<Student> studentByEmail =
        studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("Student with ID " + studentId
            + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exists"));
        if(name != null && name.length()>0 &&
                !Objects.equals(student.getName(),name)) {
                student.setName(name);
        }
        else {
            throw new IllegalStateException("Please provide name or email");
        }
        if(email != null && email.length()>0 &&
        !Objects.equals(student.getEmail(),email)) {
            if(studentRepository.findStudentByEmail(email).isPresent()) {
                throw  new IllegalStateException("email taken");
            }

            student.setEmail(email);
        }
    }
}
