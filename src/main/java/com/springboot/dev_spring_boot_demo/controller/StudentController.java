package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Student;
import com.springboot.dev_spring_boot_demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/students")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list-student")
    public String list(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "admin/students/list-student";
    }

    @GetMapping("/student-form-insert")
    public String formInsert(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "admin/students/student-form-insert";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/students/student-form-insert";
        }
        studentService.save(student);
        return "redirect:/admin/students/list-student";
    }

    @GetMapping("/student-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Student student = studentService.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "admin/students/student-form-update";
        }
        return "admin/students/list-student";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        studentService.deleteById(id);
        return "redirect:/admin/students/list-student";
    }
}
