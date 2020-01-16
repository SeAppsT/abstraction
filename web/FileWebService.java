package com.example.beck.web;

import com.example.beck.exception.EntityNotFoundException;
import com.example.beck.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller("/file")
public class FileWebService {
    private FileService fileService;

    @Autowired
    public FileWebService(FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/{id}")
    public void uploadFile(@PathVariable Long id, MultipartFile file) throws IOException, EntityNotFoundException {
        this.fileService.uploadFile(id, file);
    }
}