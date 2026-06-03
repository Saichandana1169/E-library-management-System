package com.library.library_management.repository;

import com.library.library_management.entity.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedBookRepository
        extends JpaRepository<IssuedBook, Long> {

    List<IssuedBook> findByStudentId(
            String studentId
    );

    List<IssuedBook> findByStudentIdAndStatus(
            String studentId,
            String status
    );

    List<IssuedBook> findByBookId(
            Long bookId
    );

    IssuedBook findFirstByStudentIdAndBookIdAndStatus(
            String studentId,
            Long bookId,
            String status
    );
}