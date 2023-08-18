package com.example.demo.service;

import com.example.demo.dto.StudentCreateDTO;
import com.example.demo.dto.StudentUpdateDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addNewStudent(StudentCreateDTO studentCreateDTO) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentCreateDTO.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        Student newStudent = new Student();
        newStudent.setName(studentCreateDTO.getName());
        newStudent.setEmail(studentCreateDTO.getEmail());
        // Set other fields if needed

        studentRepository.save(newStudent);
    }


    @Override
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Long studentId, StudentUpdateDTO updatedStudentDTO) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);
        //if (existingStudentOptional.isEmpty()) {
        //    throw new IllegalStateException("Student with id " + studentId + " does not exist");
        //}

        Student existingStudent = existingStudentOptional.get();
        existingStudent.setEmail(updatedStudentDTO.getEmail());
        existingStudent.setName(updatedStudentDTO.getName());
        // Update other fields as needed

        studentRepository.save(existingStudent);
    }

}
