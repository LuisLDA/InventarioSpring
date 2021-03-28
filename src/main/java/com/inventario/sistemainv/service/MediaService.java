package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.domain.Media;

import java.util.List;

public interface MediaService {

    List<Media> listMedia();
    void addMedia(Media media);
    void deleteMedia(Media media);
    Media mediaporid(Long id);
    String extencion(String nombre);
    Media searchbyFile_name(String file_name);
    void actualizarMedia(Long id);

}
