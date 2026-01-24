package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.MenuResponseDTO;
import com.mavani.hotelApi.dto.UserRequestDTO;
import com.mavani.hotelApi.dto.UserResponseDTO;
import com.mavani.hotelApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> find(@PathVariable Long userId){

        UserResponseDTO responseDTO = userService.findById(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save (@RequestBody  UserRequestDTO requestDTO){
        UserResponseDTO responseDTO = userService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update (@RequestBody  UserRequestDTO requestDTO, @PathVariable Long userId){
        UserResponseDTO responseDTO = userService.update(userId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> delete(@PathVariable Long menuId) {
        String msg = userService.deleteById(menuId);
        MenuResponseDTO menuResponseDTO = new  MenuResponseDTO(msg);
        return ResponseEntity.ok().body(menuResponseDTO);


    };
}
