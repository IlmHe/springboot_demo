package com.example.demo.student;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student test1 = new Student(
                    "Mariam",
                    "mariam.a@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );
            Student test2 = new Student(
                    "Mariam2",
                    "mariam2.22a@gmail.com",
                    LocalDate.of(2002, JANUARY, 2)
            );

            studentRepository.saveAll(
                    List.of(test1, test2));
        };
    }
}

