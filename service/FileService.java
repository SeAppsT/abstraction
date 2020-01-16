package com.example.beck.service;

import com.example.beck.domain.Media;
import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    private final MediaRepository mediaRepository;

    @Value("${file.path}")
    private String path;

    @Autowired
    public FileService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void uploadFile(Long id, MultipartFile file) throws EntityNotFoundException, IOException {
        Media media = this.mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String fullPath = this.path + UUID.randomUUID().toString() + file.getOriginalFilename();
        file.transferTo(new File(fullPath));
        media.setPath(fullPath);
        this.mediaRepository.save(media);
    }
}