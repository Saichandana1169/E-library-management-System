package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {

        return bookRepository
                .findById(id)
                .orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void reduceStock(Long bookId) {

        Book book = getBookById(bookId);

        if (book != null &&
                book.getStock() > 0) {

            book.setStock(
                    book.getStock() - 1
            );

            bookRepository.save(book);
        }
    }

    public void increaseStock(Long bookId) {

        Book book = getBookById(bookId);

        if (book != null) {

            book.setStock(
                    book.getStock() + 1
            );

            bookRepository.save(book);
        }
    }
}