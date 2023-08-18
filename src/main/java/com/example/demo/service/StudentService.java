package com.example.demo.service;

import com.example.demo.model.StudentCreate;
import com.example.demo.model.StudentUpdate;
import com.example.demo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    void addNewStudent(StudentCreate studentCreate);
    void deleteStudent(Long studentId);
    void updateStudent(Long studentId, StudentUpdate updatedStudentDTO);
}
