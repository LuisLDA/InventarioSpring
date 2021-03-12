package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.MediaDao;
import com.inventario.sistemainv.domain.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService{

    @Autowired
    private MediaDao mediaDao;
    @Override
    public List<Media> listMedia() {
        return (List<Media>) mediaDao.findAll();
    }
}
