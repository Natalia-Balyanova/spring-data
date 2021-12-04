package com.gb.balyanova.springdata.controllers;

import com.gb.balyanova.springdata.entities.Product;
import com.gb.balyanova.springdata.exceptions.ResourceNotFoundException;
import com.gb.balyanova.springdata.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found, id: " + id));
//        if(product.isPresent()) {
//            return new ResponseEntity<>(product.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Product Not Found, id: " + id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changePrice(productId, delta);
    }

    @GetMapping("/products/price_between")
    public List<Product> findProductsByPriceBetween(@RequestParam(defaultValue = "0") Integer min,@RequestParam(defaultValue = "1200") Integer max) {
        return productService.findByPriceBetween(min, max);
    }

    @GetMapping("/products/price_low")
    public List<Product> findLowPriceProduct(@RequestParam(defaultValue = "100") Integer min) {
        return productService.findLowPriceProducts(min);
    }

    @GetMapping("/products/price_high")
    public List<Product> findMoreThanValue(@RequestParam(defaultValue = "1000") Integer max) {
        return productService.findMoreThanValue(max);
    }
}
