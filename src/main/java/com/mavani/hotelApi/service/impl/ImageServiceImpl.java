package com.mavani.hotelApi.service.impl;

import com.mavani.hotelApi.common.enums.ImgType;
import com.mavani.hotelApi.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.base.path}")
    private String basePath;

    @Override
    public byte[] getImageById(ImgType imgType, Long imgId, int size) {

        try {

            Path path = Paths.get(basePath, imgType.name(), imgId + ".jpg");

            if (!Files.exists(path)) {
                path = Paths.get(basePath, "default.png");
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Thumbnails.of(path.toFile())
                    .width(size)
                    .keepAspectRatio(true)
                    .outputFormat("jpg")
                    .toOutputStream(baos);

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Unable to load image");
        }
    }


    private byte[] getDefaultImage() {
        try {
            return Files.readAllBytes(Paths.get(basePath, "default.jpg"));
        } catch (IOException e) {
            throw new RuntimeException("Default image missing");
        }
    }
}

