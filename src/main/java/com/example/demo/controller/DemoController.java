package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/product")
    @ResponseBody
    public Product getProductDetails() {
        return new Product(1, "Sample Product", 19.99, true);
    }
}
