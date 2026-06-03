package com.library.library_management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "issued_book")
public class IssuedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    private String studentId;

    private Long bookId;

    private String bookName;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private Double fine;

    private String status;

    public IssuedBook() {
    }

    public IssuedBook(String studentId,
                      Long bookId,
                      String bookName,
                      LocalDate issueDate,
                      LocalDate dueDate,
                      LocalDate returnDate,
                      Double fine,
                      String status) {

        this.studentId = studentId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.status = status;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}