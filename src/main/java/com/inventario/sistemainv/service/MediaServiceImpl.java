package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.MediaDao;
import com.inventario.sistemainv.domain.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MediaServiceImpl implements  MediaService{

    @Autowired
    private MediaDao mediaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Media> listMedia() {
        List<Media> listamedia = (List<Media>) mediaDao.findAll();
        return listamedia;
    }

    @Override
    @Transactional
    public void addMedia(Media media) {
        mediaDao.save(media);
    }

    @Override
    @Transactional
    public void deleteMedia(Media media) {
        mediaDao.delete(media);
    }

    @Override
    @Transactional(readOnly = true)
    public Media mediaporid(Long id) {
        Media media = mediaDao.mediaporid(id);
        return media;
    }

    public String extencion(String nombre) {

        Pattern pattern = Pattern.compile(".jpg|.png|.jpeg");
        Matcher matcher = pattern.matcher(nombre);
        if (matcher.find()){
            return matcher.group();
        } else {
            return " ";
        }

    }
}
