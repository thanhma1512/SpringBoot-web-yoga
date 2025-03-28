package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list-product")
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/products/list-product";
    }

    @GetMapping("/product-form-insert")
    public String formInsert(Model model) {
        model.addAttribute("product", new Product());
        return "admin/products/product-form-insert";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") @Valid Product product,
                              BindingResult bindingResult,
                              @RequestParam("imageName") String imageName) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "admin/products/product-form-insert";
        }
        // Set the selected image name
        product.setImage(imageName);
        // Save the product to the database
        productService.saveProduct(product);
        return "redirect:/admin/products/list-product";
    }


    @GetMapping("/form")
    public String showInsertForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        // Lấy danh sách ảnh từ thư mục static/default/images/
        List<String> imageList = new ArrayList<>();
        String imageDir = "src/main/resources/static/default/images/";
        File folder = new File(imageDir);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
            if (files != null) {
                for (File file : files) {
                    imageList.add(file.getName());
                }
            }
        }
        model.addAttribute("imageList", imageList);
        return "admin/products/product-form-insert"; // Trả về form
    }



    @GetMapping("/product-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "admin/products/product-form-update";
        }
        return "redirect:/admin/products/list-product";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
        }
        return "redirect:/admin/products/list-product";
    }
}