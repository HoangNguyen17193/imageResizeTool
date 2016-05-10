package com.image.resize.tool.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.image.resize.tool.exception.GeneralException;

public class FileService {
    public static List<String> filterFilePaths(String folderPath) {
        List<String> filePaths = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(Paths.get(folderPath))) {
            filePaths = pathStream.map(String::valueOf).filter(path -> filterFilePath(path))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new GeneralException("cannot get file paths", e);
        }
        return filePaths;
    }

    private static boolean filterFilePath(String path) {
        for (String fileExtention : Constants.FILE_EXTENSIONS) {
            if (path.toLowerCase().endsWith(fileExtention)) {
                return true;
            }
        }
        return false;
    }
}
