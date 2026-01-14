package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.LoginRequestDTO;
import com.mavani.hotelApi.dto.LoginResponseDTO;
import com.mavani.hotelApi.dto.RegisterRequestDTO;
import com.mavani.hotelApi.dto.RegisterResponseDTO;
import com.mavani.hotelApi.security.JwtService;
import com.mavani.hotelApi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "**/")
public class AuthController {
    AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO request) {
        Map<String, Object> data = new HashMap<>();
        String token = jwtService.generateToken(request.getEmail());
        data.put("email", request.getEmail());
        data.put("role", "USER");
        data.put("token", token);

        return ResponseEntity.status(200).body(data);

    }


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequestDTO requestDTO) {
        RegisterResponseDTO responseDTO =  authService.saveUser(requestDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("Result", responseDTO);
        return ResponseEntity.status(201).body(data);
    }


}