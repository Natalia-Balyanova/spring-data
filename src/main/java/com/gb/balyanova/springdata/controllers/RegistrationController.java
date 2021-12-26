package com.gb.balyanova.springdata.controllers;

import com.gb.balyanova.springdata.converter.ProductConverter;
import com.gb.balyanova.springdata.dto.JwtRequest;
import com.gb.balyanova.springdata.dto.JwtResponse;
import com.gb.balyanova.springdata.entities.User;
import com.gb.balyanova.springdata.exceptions.UserIsAlreadyExistsException;
import com.gb.balyanova.springdata.services.UserService;
import com.gb.balyanova.springdata.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final ProductConverter converter;
    private final UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody JwtRequest userRequest) throws UserIsAlreadyExistsException {
        UserDetails userDetails = userService.saveUser(converter.jwtRequestToUser(userRequest));
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
        }
    }