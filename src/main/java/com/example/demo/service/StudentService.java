package com.example.demo.service;

import com.example.demo.dto.StudentCreateDTO;
import com.example.demo.dto.StudentUpdateDTO;
import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    void addNewStudent(StudentCreateDTO studentCreateDTO);
    void deleteStudent(Long studentId);
    void updateStudent(Long studentId, StudentUpdateDTO updatedStudentDTO);
}
