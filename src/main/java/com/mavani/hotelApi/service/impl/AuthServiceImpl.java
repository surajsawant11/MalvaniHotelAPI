package com.mavani.hotelApi.service.impl;

import com.mavani.hotelApi.common.exception.ValidationException;
import com.mavani.hotelApi.dto.LoginRequestDTO;
import com.mavani.hotelApi.dto.LoginResponseDTO;
import com.mavani.hotelApi.dto.RegisterRequestDTO;
import com.mavani.hotelApi.dto.RegisterResponseDTO;
import com.mavani.hotelApi.model.UserModel;
import com.mavani.hotelApi.repo.UserRepository;
import com.mavani.hotelApi.security.JwtService;
import com.mavani.hotelApi.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;

    }

    public RegisterResponseDTO saveUser(RegisterRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ValidationException("Email already exists");
        }

        UserModel user = new UserModel();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword()); // (later encrypt)
        user.setRole("USER");

        user = userRepository.save(user);
        user.setCreatedBy(user);
        user.setUpdatedBy(user);
        user = userRepository.save(user);

        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setName(user.getName());
        responseDTO.setMsg("User added successfully");
        return responseDTO;
    }


    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ValidationException("Invalid credentials"));

        // plain password compare (works but not secure)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ValidationException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getName(),
                user.getId(),
                user.getRole()
        );

        return new LoginResponseDTO(
                "success",
                user.getEmail(),
                user.getRole(),
                user.getName(),
                token
        );
    }
}

