package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.MenuRequestDTO;
import com.mavani.hotelApi.dto.MenuResponseDTO;
import com.mavani.hotelApi.dto.RegisterResponseDTO;
import com.mavani.hotelApi.service.ImageService;
import com.mavani.hotelApi.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {

    final MenuService menuService;
    final ImageService imageService;


    @GetMapping
    public ResponseEntity<?> find (){
        MenuRequestDTO requestDTO = new MenuRequestDTO();
        List<MenuResponseDTO> responseDTOList = menuService.findAll(requestDTO);
        return ResponseEntity.ok(responseDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MenuRequestDTO menuRequestDTO){
        MenuResponseDTO menuResponseDTO = menuService.save(menuRequestDTO);
        return  ResponseEntity.ok().body(menuResponseDTO);
    }

    @PutMapping(value = "/{menuId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MenuResponseDTO> update(
            @ModelAttribute MenuRequestDTO dto,
            @PathVariable Long menuId) {

        MenuResponseDTO response = menuService.update(dto, menuId);

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {

            boolean uploaded = imageService.uploadImage("MENU", dto.getImage(), menuId);

            if (uploaded) {
                response.setMessage(response.getMessage() + " Image uploaded successfully.");
            } else {
                response.setMessage(response.getMessage() + " Image upload failed.");
            }
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{menuId}")
    public ResponseEntity<?> findById (@PathVariable Long menuId){
        MenuRequestDTO requestDTO = new MenuRequestDTO();
        MenuResponseDTO responseDTO = menuService.findById(menuId);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> delete(@PathVariable Long menuId) {
        String msg = menuService.deleteById(menuId);
        MenuResponseDTO menuResponseDTO = new  MenuResponseDTO(msg);
        return ResponseEntity.ok().body(menuResponseDTO);


    };
}
