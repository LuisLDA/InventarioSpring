package com.inventario.sistemainv.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    public String add_media(MultipartFile foto) throws IOException;
    public boolean delete(String filename);

}
