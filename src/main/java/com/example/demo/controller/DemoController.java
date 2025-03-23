package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/products")
public class DemoController {

    private final List<Product> productList = new ArrayList<>();

    public DemoController() {
        // Adding some sample products to the list
        productList.add(new Product(1, "Sample Product 1", 19.99, true));
        productList.add(new Product(2, "Sample Product 2", 29.99, false));
    }

    // GET: Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productList;
    }

    // GET: Retrieve a product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null); // Return null if product not found
    }

    // POST: Add a new product
    @PostMapping
    public String addProduct(@RequestBody Product product) {
        productList.add(product);
        return "Product added successfully!";
    }

    // PUT: Update an existing product
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setAvailable(updatedProduct.isAvailable());
                return "Product updated successfully!";
            }
        }
        return "Product not found!";
    }

    // DELETE: Remove a product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean removed = productList.removeIf(product -> product.getId() == id);
        return removed ? "Product deleted successfully!" : "Product not found!";
    }
}
