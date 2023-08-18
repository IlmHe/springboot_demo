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

@Transactional
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
    public void updateStudent(Long studentId, StudentUpdateDTO updatedStudentDTO) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        Student existingStudent = existingStudentOptional.get();
        existingStudent.setEmail(updatedStudentDTO.getEmail());
        existingStudent.setName(updatedStudentDTO.getName());

        studentRepository.save(existingStudent);
    }

}
