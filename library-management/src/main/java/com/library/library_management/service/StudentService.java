package com.library.library_management.service;

import com.library.library_management.entity.Student;
import com.library.library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student login(String studentId,
                         String password) {

        return studentRepository
                .findByStudentIdAndPassword(
                        studentId,
                        password
                );
    }

    public Student getStudentById(
            String studentId) {

        return studentRepository
                .findById(studentId)
                .orElse(null);
    }
}