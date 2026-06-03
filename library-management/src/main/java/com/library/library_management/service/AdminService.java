package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.entity.Student;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean adminLogin(String id, String password) {
        return id.equals("admin")
                && password.equals("admin@123");
    }
}