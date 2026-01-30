package com.mavani.hotelApi.service;

import com.mavani.hotelApi.common.enums.ImgType;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {


    public byte[] getImageById(ImgType imgType, Long imgId, int size);

    public Boolean uploadImage(String imageType, MultipartFile image, Long id);

}
