package com.library.library_management.repository;

import com.library.library_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, String> {

    Student findByStudentIdAndPassword(
            String studentId,
            String password
    );

    boolean existsByStudentId(
            String studentId
    );
}