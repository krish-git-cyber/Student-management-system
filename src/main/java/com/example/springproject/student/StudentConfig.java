package com.example.springproject.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Maria = new Student(
                    "Maria",
                    "maria@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,14)

            );
            Student Alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.FEBRUARY,10)

            );
            Student Jane = new Student(
                    "Jane",
                    "jane@gmail.com",
                    LocalDate.of(2003, Month.FEBRUARY,15)

            );
            repository.saveAll(
                    List.of(Maria,Alex,Jane)
            );

        };
    }

}
