package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.domain.Media;

import java.util.List;

public interface MediaService {

    public List<Media> listMedia();
    public void addMedia(Media media);
    public void deleteMedia(Media media);
    public Media mediaporid(Long id);
    public String extencion(String nombre);

}
