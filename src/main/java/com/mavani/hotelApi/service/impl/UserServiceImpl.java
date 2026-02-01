package com.mavani.hotelApi.service.impl;

import com.mavani.hotelApi.dto.UserRequestDTO;
import com.mavani.hotelApi.dto.UserResponseDTO;
import com.mavani.hotelApi.model.UserModel;
import com.mavani.hotelApi.repo.UserRepository;
import com.mavani.hotelApi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDTO> findAll(UserRequestDTO requestDTO) {
        List<UserModel> userModelList =  userRepository.findAll();
        List<UserResponseDTO> responseDTOList = new ArrayList<>();
        userModelList.forEach(a->{
            responseDTOList.add(new UserResponseDTO(a.getId(),a.getName(),a.getPhone(),a.getEmail(),a.getRole(),a.getUpdatedDt(),a.getCreatedDt(),a.getCreatedBy().getName(),a.getUpdatedBy().getName(),"http://localhost:8080/malvanihotel/img/get/PROFILE/500/"+a.getId(), "All Record Find Successfully"));
        });
        return responseDTOList;
    }

    @Override
    public UserResponseDTO save(UserRequestDTO requestDTO) {

        UserModel user = new UserModel();
        if(requestDTO.getUserId() != null){
            user.setId(requestDTO.getUserId());
        }
        user.setEmail(requestDTO.getEmail());
        user.setRole(requestDTO.getRole());
        user.setName(requestDTO.getName());
        user.setPhone(requestDTO.getPhone());
        user.setPassword(requestDTO.getPassword());

//        user = userRepository.save(user);


//        com.mavani.hotelApi.dto.UserResponseDTO
//@Contract(pure = true)
//public UserResponseDTO(
//                Long userId,
//                String name,
//                String phone,
//                String email,
//                String role,
//                LocalDateTime updatedDt,
//                LocalDateTime createdDt,
//                String createdBy,
//                String updatedBy
//        )
        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getRole(),
                user.getUpdatedDt(), user.getCreatedDt(), user.getCreatedBy().getName(),user.getUpdatedBy().getName(),"http://localhost:8080/malvanihotel/img/get/PROFILE/500/"+user.getId(),"User Save Successfully");



        return userResponseDTO;
    }

    @Override
    public UserResponseDTO update(Long userId , UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);
        if(optionalUserModel.isPresent()) {
            UserModel userModel = optionalUserModel.get();
            userModel.setName(requestDTO.getName());
            userModel.setRole(requestDTO.getRole());
            userModel.setPhone(requestDTO.getPhone());
            userModel.setEmail(requestDTO.getEmail());
            userModel = userRepository.save(userModel);
            responseDTO = new UserResponseDTO(userModel.getId(),userModel.getName(),userModel.getPhone(),
                    userModel.getEmail(), userModel.getRole(),userModel.getUpdatedDt(), userModel.getCreatedDt(), userModel.getCreatedBy().getName(), userModel.getUpdatedBy().getName(),"http://localhost:8080/malvanihotel/img/get/PROFILE/500/"+userModel.getId(),"User Update Successfully");
        }
        return responseDTO;
    }

    @Override
    public UserResponseDTO findById(Long userId) {
        UserResponseDTO responseDTO = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);
        if(optionalUserModel.isPresent()){
            UserModel userModel = optionalUserModel.get();
            responseDTO = new UserResponseDTO(userModel.getId(),userModel.getName(),userModel.getPhone(),
                    userModel.getEmail(), userModel.getRole(),userModel.getUpdatedDt(), userModel.getCreatedDt(), userModel.getCreatedBy().getName(), userModel.getUpdatedBy().getName(),"http://localhost:8080/malvanihotel/img/get/PROFILE/500/"+userModel.getId(), "Record Find Successfully");
        }
        return responseDTO;
    }

    @Override
    public String deleteById(Long menuId) {
        userRepository.deleteById(menuId);
        return "User Is Delete Successfully" ;
    }
}
