package com.mavani.hotelApi.service.impl;

import com.mavani.hotelApi.dto.RegisterRequestDTO;
import com.mavani.hotelApi.dto.RegisterResponseDTO;
import com.mavani.hotelApi.model.UserModel;
import com.mavani.hotelApi.repo.UserRepository;
import com.mavani.hotelApi.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService  {
    private UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public RegisterResponseDTO saveUser(RegisterRequestDTO registerRequestDTO){
        UserModel user = new UserModel();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPhone(registerRequestDTO.getPhone());
        user.setPassword(registerRequestDTO.getPassword());
        user.setRole("USER");
//        userRepository.save(user);
        user =  userRepository.save(user);
        user.setCreatedBy(user);
        user.setUpdatedBy(user);
        user = userRepository.save(user);

        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setName(user.getName());
        responseDTO.setMsg("user added successfully");
        return responseDTO;
    }

}
