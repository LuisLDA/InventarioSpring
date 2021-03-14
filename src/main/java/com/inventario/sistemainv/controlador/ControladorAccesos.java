package com.inventario.sistemainv.controlador;


import com.inventario.sistemainv.domain.User;
import com.inventario.sistemainv.domain.UserGroup;
import com.inventario.sistemainv.service.UserGroupService;
import com.inventario.sistemainv.service.UserDetailService;
import com.inventario.sistemainv.service.UserService;
import com.inventario.sistemainv.util.EncryptPass;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//       _
//      / \    ___  ___  ___  ___   ___   ___
//     / _ \  / __|/ __|/ _ \/ __| / _ \ / __|
//    / ___ \| (__| (__|  __/\__ \| (_) |\__ \
//   /_/   \_\\___|\___|\___||___/ \___/ |___/
//
@Controller
@Slf4j
@RequestMapping("/accesos") //localhost:8080/categorias/
public class ControladorAccesos {
    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserService userService;

    //======================================GRUPOS=========================================
    @GetMapping("/grupos")
    public String controlGrupos(Model model) {
        model.addAttribute("pageTitle", "Accesos Grupos");
        log.info("Acceso a grupos");
        var grupos = userGroupService.listGroup();
        model.addAttribute("groups", grupos);
        return "accesos_grupos";
    }

    @GetMapping("/grupos/eliminar_grupo/{id}")
    public String eliminarGrupo(UserGroup userGroup) {
        log.info("Se eliminino el grupo: " + userGroup);
        userGroupService.deleteGroup(userGroup);
        return "redirect:/accesos/grupos";
    }
/*
    @GetMapping("/editar_categoria/{id}")
    public String editarGrupo(@Validated UserGroup userGroup, Model model) {
        model.addAttribute("pageTitle","Editar Categoria");
        log.info("Se va editar el grupo: " + userGroup);
        userGroup = userGroupService.searchGroup(userGroup);
        model.addAttribute("categoria", userGroup);
        return "edit_accesos_grupos";
    }*/

    @PostMapping("/grupos/add_grupo")
    public String agregarGrupo(@Validated UserGroup userGroup) {
        log.info("Se agregara :" + userGroup);
        try {
            userGroupService.saveGroup(userGroup);
            log.info("Agregado el grupo :" + userGroup);
            return "redirect:/accesos/grupos";
        }catch (DataIntegrityViolationException e){
            log.error("ERROR AL AGREGAR O MODIFICAR",e);
            return "accesos_grupos";
        }

    }


    //======================================USUARIOS=========================================
    @GetMapping("/usuarios")
    public String controlUsuarios(Model model) {
        model.addAttribute("pageTitle", "Accesos Usuarios");
        log.info("Acceso a usuarios");
        var usuarios = userService.listUser();
        model.addAttribute("usuarios", usuarios);
        var grupos = userGroupService.listGroup();
        model.addAttribute("grupos", grupos);
        return "accesos_usuarios";
    }

    @PostMapping("/usuarios/add_user")
    public String agregarUsuario(@Validated User user) {
        log.info("Se agregara :" + user);
        user.setPassword(EncryptPass.Encriptar(user.getPassword()));
        try {
            userService.saveUser(user);
            log.info("Agregado el usuario :" + user);
            return "redirect:/accesos/usuarios";
        }catch (DataIntegrityViolationException e){
            log.error("ERROR AL AGREGAR O MODIFICAR",e);
            return "accesos_usuarios";
        }

    }
}
