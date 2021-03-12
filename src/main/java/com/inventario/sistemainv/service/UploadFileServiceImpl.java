package com.inventario.sistemainv.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements UploadFileService{

    public final static String IMAGENES_FOLDER = "imagenes";

    @Override
    public String add_media(MultipartFile foto) throws IOException {

        //Direcortio absoluto dentro de nuestro proyecto
        String uniqueFilename = foto.getOriginalFilename();
        Path rootpath = getPath(uniqueFilename);
        Files.copy(foto.getInputStream(), rootpath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {

        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();
        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()){
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(IMAGENES_FOLDER).resolve(filename).toAbsolutePath();
    }
}
