package com.library.library_management.controller;

import com.library.library_management.entity.Book;
import com.library.library_management.entity.Student;
import com.library.library_management.service.AdminService;
import com.library.library_management.service.BookService;
import com.library.library_management.service.IssueBookService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;

    @Autowired
    private IssueBookService issueBookService;

    @GetMapping("/home")
    public String home(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        return "admin/home";
    }

    @GetMapping("/explore")
    public String explore(Model model,
                          HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        model.addAttribute(
                "books",
                bookService.getAllBooks()
        );

        return "admin/explore";
    }

    @GetMapping("/register")
    public String registerPage(
            HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        return "admin/register";
    }

    @PostMapping("/register")
    public String registerStudent(
            Student student) {

        adminService.registerStudent(
                student
        );

        return "redirect:/admin/register";
    }

    @GetMapping("/add-book")
    public String addBookPage(
            HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/";
        }

        return "admin/add-book";
    }

    @PostMapping("/add-book")
    public String addBook(
            Book book) {

        adminService.addBook(book);

        return "redirect:/admin/explore";
    }

    @PostMapping("/issue")
    public String issueBook(
            @RequestParam String studentId,
            @RequestParam Long bookId) {

        issueBookService.issueBook(
                studentId,
                bookId
        );

        return "redirect:/admin/explore";
    }

    @PostMapping("/return")
    public String returnBook(
            @RequestParam String studentId,
            @RequestParam Long bookId) {

        issueBookService.returnBook(
                studentId,
                bookId
        );

        return "redirect:/admin/explore";
    }
}