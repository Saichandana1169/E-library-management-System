package com.library.library_management.controller;

import com.library.library_management.entity.IssuedBook;
import com.library.library_management.service.IssueBookService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IssueBookService issueBookService;

    @GetMapping("/home")
    public String home(HttpSession session) {

        if (session.getAttribute("studentId")
                == null) {

            return "redirect:/";
        }

        return "student/home";
    }

    @GetMapping("/books")
    public String books(
            Model model,
            HttpSession session) {

        String studentId =
                (String) session.getAttribute(
                        "studentId"
                );

        List<IssuedBook> books =
                issueBookService
                        .getBooksByStudent(
                                studentId
                        );

        model.addAttribute(
                "books",
                books
        );

        return "student/books";
    }

    @GetMapping("/fine")
    public String fine(
            Model model,
            HttpSession session) {

        String studentId =
                (String) session.getAttribute(
                        "studentId"
                );

        double totalFine =
                issueBookService
                        .getTotalFine(
                                studentId
                        );

        model.addAttribute(
                "totalFine",
                totalFine
        );

        return "student/fine";
    }
}