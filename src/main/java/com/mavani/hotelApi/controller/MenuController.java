package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.dto.MenuRequestDTO;
import com.mavani.hotelApi.dto.MenuResponseDTO;
import com.mavani.hotelApi.dto.RegisterResponseDTO;
import com.mavani.hotelApi.service.MenuService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    MenuService menuService;
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

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

    @PutMapping("/{menuId}")
    public ResponseEntity<?> update(@RequestBody MenuRequestDTO menuRequestDTO, @PathVariable Long menuId){
        MenuResponseDTO menuResponseDTO = menuService.update(menuRequestDTO, menuId);
        return  ResponseEntity.ok().body(menuResponseDTO);
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
