package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Customer;
import com.springboot.dev_spring_boot_demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list-customer")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "admin/customers/list-customer";
    }

    @GetMapping("/customer-form-insert")
    public String formInsert(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customers/customer-form-insert";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/customers/customer-form-insert";
        }
        customerService.save(customer);
        return "redirect:/admin/customers/list-customer";
    }

    @GetMapping("/customer-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "admin/customers/customer-form-update";
        }
        return "redirect:/admin/customers/list-customer";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            customerService.deleteById(id);
        }
        return "redirect:/admin/customers/list-customer";
    }
}

