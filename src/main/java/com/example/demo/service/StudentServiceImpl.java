package com.example.demo.service;

import com.example.demo.model.StudentCreate;
import com.example.demo.model.StudentUpdate;
import com.example.demo.entity.Student;
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
    public void addNewStudent(StudentCreate studentCreate) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentCreate.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        Student newStudent = new Student();
        newStudent.setName(studentCreate.getName());
        newStudent.setEmail(studentCreate.getEmail());

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
    public void updateStudent(Long studentId, StudentUpdate updatedStudentDTO) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        Student existingStudent = existingStudentOptional.get();
        existingStudent.setEmail(updatedStudentDTO.getEmail());
        existingStudent.setName(updatedStudentDTO.getName());

        studentRepository.save(existingStudent);
    }

}
