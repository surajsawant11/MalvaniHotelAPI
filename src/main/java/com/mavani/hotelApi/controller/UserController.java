package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.MenuResponseDTO;
import com.mavani.hotelApi.dto.UserRequestDTO;
import com.mavani.hotelApi.dto.UserResponseDTO;
import com.mavani.hotelApi.service.ImageService;
import com.mavani.hotelApi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private ImageService imageService;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(UserRequestDTO userRequestDTO){

        List<UserResponseDTO> userResponseDTOList = userService.findAll(userRequestDTO);
        return ResponseEntity.ok(userResponseDTOList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> find(@PathVariable Long userId){

        UserResponseDTO responseDTO = userService.findById(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDTO> save (@ModelAttribute  UserRequestDTO requestDTO){
        UserResponseDTO responseDTO = userService.save(requestDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping(path = "/{userId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDTO> update (@ModelAttribute  UserRequestDTO requestDTO, @PathVariable Long userId){
        UserResponseDTO response = userService.update(userId, requestDTO);

//        MenuResponseDTO response = menuService.update(dto, menuId);

        if (requestDTO.getImage() != null && !requestDTO.getImage().isEmpty()) {

            boolean uploaded = imageService.uploadImage("PROFILE", requestDTO.getImage(), userId);

            if (uploaded) {
                response.setMessage(response.getMessage() + " Image uploaded successfully.");
            } else {
                response.setMessage(response.getMessage() + " Image upload failed.");
            }
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> delete(@PathVariable Long menuId) {
        String msg = userService.deleteById(menuId);
        MenuResponseDTO menuResponseDTO = new  MenuResponseDTO(msg);
        return ResponseEntity.ok().body(menuResponseDTO);


    };
}
