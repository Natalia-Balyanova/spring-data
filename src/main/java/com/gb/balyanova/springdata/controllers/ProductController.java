package com.gb.balyanova.springdata.controllers;

import com.gb.balyanova.springdata.dto.ProductDto;
import com.gb.balyanova.springdata.entities.Product;
import com.gb.balyanova.springdata.exceptions.ResourceNotFoundException;
import com.gb.balyanova.springdata.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if(page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, titlePart, page).map(
                p -> new ProductDto(p)
        );
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(null);//здесь занулили
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return productService.save(product);
    }
    //проверяла метод в постмане. Проверка на несуществующий id (напр, 1000) не работает,
    //он все равно записывает продукт в конец списка и создает новый продукт с id 21, 22 и т.д.
    //остальной функционал работает
    @PutMapping
    public Product updateProduct(@RequestBody ProductDto productDto) {
        if(!productService.findById(productDto.getId()).isPresent()){
            throw new ResourceNotFoundException("Product Not Found, id: " + productDto.getId());
        }
        Product updateProduct = new Product();
        updateProduct.setId(productDto.getId());
        updateProduct.setTitle(productDto.getTitle());
        updateProduct.setPrice(productDto.getPrice());
        return productService.save(updateProduct);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found, id: " + id));
//        if(product.isPresent()) {
//            return new ResponseEntity<>(product.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Product Not Found, id: " + id), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

//    @GetMapping("/change_price")
//    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
//        productService.changePrice(productId, delta);
//    }
//
//    @GetMapping("/price_low")
//    public List<Product> findLowPriceProduct(@RequestParam(defaultValue = "100") Integer min) {
//        return productService.findLowPriceProducts(min);
//    }
//
//    @GetMapping("/price_high")
//    public List<Product> findMoreThanValue(@RequestParam(defaultValue = "1000") Integer max) {
//        return productService.findMoreThanValue(max);
//    }
}