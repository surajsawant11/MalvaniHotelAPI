package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.LoginRequestDTO;
import com.mavani.hotelApi.dto.LoginResponseDTO;
import com.mavani.hotelApi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "**/")
public class AuthController {

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>>  login(@RequestBody LoginRequestDTO request) {
        Map<String, Object> data = new HashMap<>();
        String token = jwtService.generateToken(request.getEmail());
        data.put("email",request.getEmail());
        data.put("role","USER");
        data.put("token",token);

        return ResponseEntity.status(200).body(data);
    }




}
