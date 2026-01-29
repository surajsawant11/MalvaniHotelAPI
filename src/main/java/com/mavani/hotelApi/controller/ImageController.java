package com.mavani.hotelApi.controller;

import com.mavani.hotelApi.common.enums.ImgType;
import com.mavani.hotelApi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/img")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/get/{imgType}/{size}/{imgId}")
    public ResponseEntity<byte[]> getImage(@PathVariable ImgType imgType, @PathVariable int size, @PathVariable Long imgId) {
        try {
            byte[] image = imageService.getImageById(imgType, imgId,size);
            return ResponseEntity.ok() .contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            try {
                byte[] defaultImage = Files.readAllBytes( Paths.get("images/default.png"));
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(defaultImage);
            } catch (IOException io) {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
