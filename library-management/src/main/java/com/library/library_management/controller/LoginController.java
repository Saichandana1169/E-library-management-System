package com.library.library_management.controller;

import com.library.library_management.entity.Student;
import com.library.library_management.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id,
                        @RequestParam String password,
                        HttpSession session) {

        // Admin Login
        if (id.equals("admin") &&
                password.equals("admin@123")) {

            session.setAttribute("admin", "admin");

            return "redirect:/admin/home";
        }

        // Student Login
        Student student =
                studentService.login(id, password);

        if (student != null) {

            session.setAttribute(
                    "studentId",
                    student.getStudentId()
            );

            session.setAttribute(
                    "studentName",
                    student.getName()
            );

            return "redirect:/student/home";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}