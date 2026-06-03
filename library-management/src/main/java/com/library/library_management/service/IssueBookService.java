package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.entity.IssuedBook;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.IssuedBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class IssueBookService {

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Autowired
    private BookRepository bookRepository;

    /*
     * ISSUE BOOK
     */
    public void issueBook(
            String studentId,
            Long bookId) {

        Book book =
                bookRepository.findById(bookId)
                        .orElse(null);

        if (book == null) {
            return;
        }

        if (book.getStock() <= 0) {
            return;
        }

        book.setStock(
                book.getStock() - 1
        );

        bookRepository.save(book);

        IssuedBook issuedBook =
                new IssuedBook();

        issuedBook.setStudentId(
                studentId
        );

        issuedBook.setBookId(
                book.getBookId()
        );

        issuedBook.setBookName(
                book.getBookName()
        );

        issuedBook.setIssueDate(
                LocalDate.now()
        );

        issuedBook.setDueDate(
                LocalDate.now().plusDays(2)
        );

        issuedBook.setFine(0.0);

        issuedBook.setStatus(
                "ISSUED"
        );

        issuedBookRepository.save(
                issuedBook
        );
    }

    /*
     * RETURN BOOK
     */
    public void returnBook(
            String studentId,
            Long bookId) {

        IssuedBook issuedBook =
                issuedBookRepository
                        .findFirstByStudentIdAndBookIdAndStatus(
                                studentId,
                                bookId,
                                "ISSUED"
                        );

        if (issuedBook == null) {
            return;
        }

        Book book =
                bookRepository
                        .findById(
                                issuedBook.getBookId()
                        )
                        .orElse(null);

        if (book != null) {

            book.setStock(
                    book.getStock() + 1
            );

            bookRepository.save(book);
        }

        issuedBook.setReturnDate(
                LocalDate.now()
        );

        issuedBook.setStatus(
                "RETURNED"
        );

        long lateDays =
                ChronoUnit.DAYS.between(
                        issuedBook.getDueDate(),
                        issuedBook.getReturnDate()
                );

        if (lateDays > 0) {

            issuedBook.setFine(
                    lateDays * 10.0
            );

        } else {

            issuedBook.setFine(
                    0.0
            );
        }

        issuedBookRepository.save(
                issuedBook
        );
    }

    /*
     * PAY FINE
     */
    public void payFine(
            Long issueId) {

        IssuedBook issuedBook =
                issuedBookRepository
                        .findById(issueId)
                        .orElse(null);

        if (issuedBook != null) {

            issuedBook.setFine(
                    0.0
            );

            issuedBookRepository.save(
                    issuedBook
            );
        }
    }

    /*
     * STUDENT BOOKS
     */
    public List<IssuedBook> getBooksByStudent(
            String studentId) {

        return issuedBookRepository
                .findByStudentId(
                        studentId
                );
    }

    /*
     * TOTAL FINE
     */
    public double getTotalFine(
            String studentId) {

        List<IssuedBook> books =
                issuedBookRepository
                        .findByStudentId(
                                studentId
                        );

        double total = 0;

        for (IssuedBook book : books) {

            total += book.getFine();
        }

        return total;
    }
}