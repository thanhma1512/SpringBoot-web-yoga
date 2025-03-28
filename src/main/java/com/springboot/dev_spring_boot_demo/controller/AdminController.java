package com.springboot.dev_spring_boot_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/student-list")
    public String studentList(){
        return "admin/student";
    }
    @GetMapping("/customer-list")
    public String customerList(){
        return "admin/customer";
    }
}
