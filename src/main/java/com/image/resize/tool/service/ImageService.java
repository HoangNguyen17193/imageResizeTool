package com.image.resize.tool.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;

public class ImageService {

    public static void createThumbnail(String inputFilePath, String outputFolderPath, int size) throws IOException {
        InputStream inputStream = new FileInputStream(inputFilePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage thumbnail = ImageIO.read(inputStream);
        int width = thumbnail.getWidth();
        int height = thumbnail.getHeight();
        if (width > height) {
            thumbnail = Scalr.resize(thumbnail, Scalr.Mode.FIT_TO_WIDTH, size);
        } else if (height > size) {
            thumbnail = Scalr.resize(thumbnail, Scalr.Mode.FIT_TO_HEIGHT, size);
        }
        try {
            ImageIO.write(thumbnail, "jpeg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            String outputFilePath = outputFolderPath + File.separator + FilenameUtils.getBaseName(inputFilePath)
                    + ".jpg";
            FileUtils.writeByteArrayToFile(new File(outputFilePath), byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            byteArrayOutputStream.close();
        }
    }
}
