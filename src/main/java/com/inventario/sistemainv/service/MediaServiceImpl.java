package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.MediaDao;
import com.inventario.sistemainv.domain.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
