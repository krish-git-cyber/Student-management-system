package com.example.springproject.student;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// service layer
@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private static StudentRepository studentRepository;



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
                              Student student) {
        Student updateStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exists"));

        updateStudent.setName(student.getName());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setDob(student.getDob());
        updateStudent.setAge(student.getAge());

        studentRepository.save(updateStudent);


    }
}
