package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/contact")
    public String contact(){
        return "/default/contact";
    }
    @GetMapping("/online-class")
    public String logout(){
        return "/default/online-class";
    }
    @GetMapping("/classes")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "default/classes"; // Trang HTML hiển thị danh sách sản phẩm bên ngoài
    }
    @GetMapping("/class-detail/{id}")
    public String classDetail(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/default/classes"; // Nếu không tìm thấy sản phẩm, quay lại trang chủ
        }
        model.addAttribute("product", product);
        return "default/class-detail"; // Trả về trang chi tiết sản phẩm
    }
    @GetMapping("/promo")
    public String promo(){
        return "/default/promo";
    }
}
