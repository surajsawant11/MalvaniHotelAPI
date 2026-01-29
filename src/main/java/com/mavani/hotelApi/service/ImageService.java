package com.mavani.hotelApi.service;

import com.mavani.hotelApi.common.enums.ImgType;

public interface ImageService {


    byte[] getImageById(ImgType imgType, Long imgId, int size);
}
