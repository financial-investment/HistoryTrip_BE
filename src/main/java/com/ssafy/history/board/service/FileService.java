package com.ssafy.history.board.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "C:/SSAFY/uploads";

    public String save(MultipartFile file) {

        try {
            String fileName =
                    UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR, fileName);

            Files.createDirectories(path.getParent());

            file.transferTo(path.toFile());

            return "/uploads/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }

    public void delete(String fileUrl) {

        try {
            String fileName =
                    fileUrl.replace("/uploads/", "");

            Path path = Paths.get(UPLOAD_DIR, fileName);

            Files.deleteIfExists(path);

        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실패", e);
        }
    }
}