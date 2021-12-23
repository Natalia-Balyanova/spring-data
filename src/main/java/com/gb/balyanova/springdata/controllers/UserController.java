package com.gb.balyanova.springdata.controllers;

import com.gb.balyanova.springdata.entities.User;
import com.gb.balyanova.springdata.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/customer")
    //("hasRole('USER')")
    public String customerPage() {
        return "customer";
    }

    @GetMapping("/add_product")
    public String addProduct() {
        return "add product";
    }

    @GetMapping("/delete_product")
    public String deleteProduct() {
        return "delete product";
    }

    @GetMapping("/delete_user")
    public String deleteUser() {
        return "delete user";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
