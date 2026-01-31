package com.mavani.hotelApi.service.impl;

import com.mavani.hotelApi.common.enums.ImgType;
import com.mavani.hotelApi.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.base.path}")
    private String basePath;

    @Override
    public byte[] getImageById(ImgType imgType, Long imgId, int size) {

        try {

//            Path path = Paths.get(basePath, imgType.name(), imgId + ".jpg");
            Path path = findImagePath(basePath, imgType, imgId);
            if (path == null ||!Files.exists(path)) {
                path = Paths.get(basePath, "default.jpg");
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

    private Path findImagePath(String basePath, ImgType imgType, Long imgId) throws IOException {

        Path folder = Paths.get(basePath, imgType.name());

        if (!Files.exists(folder)) return null;

        try (Stream<Path> files = Files.list(folder)) {

            return files
                    .filter(p -> p.getFileName().toString().startsWith(imgId + "."))
                    .findFirst()
                    .orElse(null);
        }
    }


    @Override
    public Boolean uploadImage(String imageType, MultipartFile image, Long id) {

        try {

//            String basePath = "images"; // or from application.properties
            String folderPath;

            switch (imageType) {
                case "MENU":
                    folderPath = basePath + File.separator + "MENU";
                    break;

                default:
                    folderPath = basePath + File.separator + "DEFAULT";
                    break;
            }

            // Create directory if not exists
            Files.createDirectories(Paths.get(folderPath));

            // Get extension (jpg/png/etc)
            String originalName = image.getOriginalFilename();
            String ext = originalName.substring(originalName.lastIndexOf("."));

            // Final file name -> id.jpg
            String fileName = id + ext;

            Path finalPath = Paths.get(folderPath, fileName);

            // Save file (replace if exists)
            Files.copy(image.getInputStream(), finalPath, StandardCopyOption.REPLACE_EXISTING);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

