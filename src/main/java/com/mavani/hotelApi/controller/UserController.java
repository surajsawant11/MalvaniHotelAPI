package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.UserRequestDTO;
import com.mavani.hotelApi.dto.UserResponseDTO;
import com.mavani.hotelApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> find(UserRequestDTO userRequestDTO){

        List<UserResponseDTO> userResponseDTOList = userService.find(userRequestDTO);
        return ResponseEntity.ok(userResponseDTOList);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save (UserRequestDTO requestDTO){

        UserResponseDTO responseDTO = userService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
