package com.mavani.hotelApi.service.impl;

import com.mavani.hotelApi.common.exception.ValidationException;
import com.mavani.hotelApi.dto.MenuRequestDTO;
import com.mavani.hotelApi.dto.MenuResponseDTO;
import com.mavani.hotelApi.model.MenuModel;
import com.mavani.hotelApi.repo.MenuRepository;
import com.mavani.hotelApi.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    public MenuServiceImpl(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }


    @Override
    public List<MenuResponseDTO> findAll(MenuRequestDTO requestDTO) {
        List<MenuResponseDTO> responseDTOList = new ArrayList<>();
       List<MenuModel> modelList = menuRepository.findAll();
        modelList.forEach(menu->{
            responseDTOList.add(new MenuResponseDTO(menu.getId(),menu.getName(),menu.getDescription(),menu.getCategory(),menu.getPrice(), "find all record successfully",menu.getStatus(),"http://localhost:8080/malvanihotel/img/get/MENU/500/"+menu.getId().toString()));
        });
        return responseDTOList;
    }

    @Override
    public MenuResponseDTO save(MenuRequestDTO requestDTO) {
        MenuModel menu = new MenuModel();
        if(requestDTO.getMenuId()!=null)
            menu.setId(requestDTO.getMenuId());
        menu.setName(requestDTO.getName());
        menu.setPrice(requestDTO.getPrice());
        menu.setCategory(requestDTO.getCategory());
        menu.setDescription(requestDTO.getDescription());
        menu.setStatus(requestDTO.getStatus());
        menu = menuRepository.save(menu);


//        MenuResponseDTO menuResponseDTO =
        return new MenuResponseDTO(menu.getId(),menu.getName(),menu.getDescription(),
                menu.getCategory(),menu.getPrice(),"Menu Save successfully",menu.getStatus(),"http://localhost:8080/malvanihotel/img/get/MENU/500/");

    }

    @Override
    public String deleteById(Long menuId) {
        menuRepository.deleteById(menuId);
        return "Menu is delete successfully";
    }

    @Override
    public MenuResponseDTO findById(Long menuId) {
        Optional<MenuModel> menuModel = menuRepository.findById(menuId);
        MenuResponseDTO responseDTO = null;
        if(menuModel.isPresent()){
            responseDTO = new MenuResponseDTO(menuModel.get().getId(),menuModel.get().getName(),menuModel.get().getDescription(),menuModel.get().getCategory(),menuModel.get().getPrice(),"record find successfully", menuModel.get().getStatus(),"http://localhost:8080/malvanihotel/img/get/MENU/500/"+menuModel.get().getId());
        }else {
            throw new ValidationException("Menu Not Found");
        }

        return responseDTO;
    }

    @Override
    public MenuResponseDTO update(MenuRequestDTO requestDTO, Long menuId) {
        MenuModel menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found: " + menuId));
        menu.setId(requestDTO.getMenuId());
        menu.setName(requestDTO.getName());
        menu.setPrice(requestDTO.getPrice());
        menu.setCategory(requestDTO.getCategory());
        menu.setDescription(requestDTO.getDescription());
        menu.setStatus(requestDTO.getStatus());
        menu = menuRepository.save(menu);

        return  new MenuResponseDTO(menu.getId(),menu.getName(),menu.getDescription(),
                menu.getCategory(),menu.getPrice(), "Menu Save Successfully",menu.getStatus(),"http://localhost:8080/malvanihotel/img/get/MENU/500/");

    }



}
