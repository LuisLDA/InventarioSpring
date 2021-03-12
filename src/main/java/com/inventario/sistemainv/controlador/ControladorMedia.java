package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.dao.MediaDao;
import com.inventario.sistemainv.domain.Media;
import com.inventario.sistemainv.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


//    __  __            _  _
//   |  \/  |  ___   __| |(_)  __ _
//   | |\/| | / _ \ / _` || | / _` |
//   | |  | ||  __/| (_| || || (_| |
//   |_|  |_| \___| \__,_||_| \__,_|
//


@Controller
@Slf4j
@RequestMapping("/media")
public class ControladorMedia {

    @Autowired
    public MediaDao mediaDao;

    @Autowired
    public UploadFileService uploadFileService;

    @PostMapping("/add_Media")
    public String agregarMedia(Media media, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash){
        log.info("INICIANDO CONTROLADOR AGREGAR MEDIA...");
        if (!foto.isEmpty()){

            try {
                String filename = uploadFileService.add_media(foto);
                media.setFile_name(filename);
                media.setFile_type(".jpg");
                log.info("Agregado la imagen :" + media);
                mediaDao.save(media);
                flash.addAttribute("valido", "Imagen agregada con exito!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "redirect:/media";
    }

    @GetMapping("/delete_media/{id}")
    public String deleteMedia(@PathVariable(value = "id") Long id, Media media){
        log.info("INICIANDO CONTROLADOR ELIMINAR MEDIA...");
        Media medias = mediaDao.mediaporid(id);
        log.info("Archivo media a eliminar: " + medias);

        if (uploadFileService.delete(medias.getFile_name())){
            log.info("archivo eliminado");
        }

        log.info("media: " + media);
        mediaDao.delete(media);

        return "redirect:/media";
    }
}
