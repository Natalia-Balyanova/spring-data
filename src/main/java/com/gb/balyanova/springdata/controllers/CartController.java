package com.gb.balyanova.springdata.controllers;

import com.gb.balyanova.springdata.dto.ProductDto;
import com.gb.balyanova.springdata.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public void addProductInCard (@PathVariable Long id){
        cartService.addProductInCart(id);
    }

    @GetMapping("/cartInfo")
    public List<ProductDto> sendCardInfo (){
        return cartService.cardInfo();
    }
    @DeleteMapping("/cartInfo/{id}") public void deleteById(@PathVariable Long id) { cartService.deleteByIdFromCartId(id); }
//    @GetMapping("/cartInfo")
//    public Map<ProductDto, Integer> sendCardInfo (){
//        return cartService.cardInfo();
//    }
//    @GetMapping("/{id}")
//    public void addProductInCard (@PathVariable Long id, @PathVariable Integer count){
//    cartService.addProductInCart(id, count);
//}
//    @GetMapping("/change_amount")
//    public void changeAmount(@RequestParam Long productId, @RequestParam Integer amount) {
//        cartService.changeAmount(productId, amount);
//    }
}
