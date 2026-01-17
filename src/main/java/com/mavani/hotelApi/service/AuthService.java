package com.mavani.hotelApi.service;

import com.mavani.hotelApi.dto.LoginRequestDTO;
import com.mavani.hotelApi.dto.LoginResponseDTO;
import com.mavani.hotelApi.dto.RegisterRequestDTO;
import com.mavani.hotelApi.dto.RegisterResponseDTO;

public interface AuthService {
    public RegisterResponseDTO saveUser(RegisterRequestDTO registerRequestDTO);

    LoginResponseDTO login(LoginRequestDTO request);
}
