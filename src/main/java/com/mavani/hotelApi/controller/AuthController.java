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
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(Map.of("result",response));
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO requestDTO) {

        Map<String, Object> data = new HashMap<>();

        try {
            RegisterResponseDTO responseDTO = authService.saveUser(requestDTO);
            data.put("result", responseDTO);
            return ResponseEntity.status(201).body(data);

        } catch (RuntimeException ex) {
            data.put("message", ex.getMessage());
            return ResponseEntity.status(400).body(data); // ✅ return data not ex
        }
    }



}