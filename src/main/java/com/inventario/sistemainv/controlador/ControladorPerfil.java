package com.inventario.sistemainv.controlador;
//    ____               __  _  _
//   |  _ \  ___  _ __  / _|(_)| |
//   | |_) |/ _ \| '__|| |_ | || |
//   |  __/|  __/| |   |  _|| || |
//   |_|    \___||_|   |_|  |_||_|
//

import com.inventario.sistemainv.dao.MediaDao;
import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.domain.Media;
import com.inventario.sistemainv.domain.User;
import com.inventario.sistemainv.service.MediaService;
import com.inventario.sistemainv.service.UploadFileService;
import com.inventario.sistemainv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/perfil")
public class ControladorPerfil {

    @Autowired
    public MediaDao mediaDao;

    @Autowired
    public UploadFileService uploadFileService;

    @Autowired
    public MediaService mediaService;

    @Autowired
    private UserService userService;


    @PostMapping("/change_avatar")
    public String changeAvatar(Media media,
                               Model model,
                               @Validated User user,
                               @RequestParam("file") MultipartFile foto
                               /*,RedirectAttributes flash*/) {
        log.info("Cambiando foto de perfil...");
        user = userService.searchUser(user);
        if (!foto.isEmpty()) {
            try {
                String filename = uploadFileService.add_media(foto);
                user.setImage(filename);
                media.setFile_name(filename);
                media.setFile_type(mediaService.extencion(filename));
                log.info("Agregado la imagen :" + media);
                mediaDao.save(media);
                userService.saveUser(user);
               // flash.addFlashAttribute("success", "Imagen " + filename + " agregada con exito!");
            } catch (IOException e) {
                log.error("ERROR:" + e.getMessage());
            }
        } else {
           // flash.addFlashAttribute("error", "No se ha seleccionado ninguna imagen");
        }
        return "redirect:/perfil";
    }


    @PostMapping("/edit_user")
    public String editUser(Model model,User user){


        return "redirect:/perfil";
    }

    @PostMapping("/change_pass")
    public String editPass(Model model,User user){


        return "redirect:/perfil";
    }

}
