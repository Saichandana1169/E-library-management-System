package com.library.library_management.controller;

import com.library.library_management.entity.IssuedBook;
import com.library.library_management.service.IssueBookService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fine")
public class FineController {

    @Autowired
    private IssueBookService issueBookService;

    @PostMapping("/pay")
    public String payFine(
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

        for (IssuedBook book : books) {

            if (book.getFine() > 0) {

                issueBookService
                        .payFine(
                                book.getIssueId()
                        );
            }
        }

        return "redirect:/student/fine";
    }
}