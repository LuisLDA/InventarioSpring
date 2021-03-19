package com.inventario.sistemainv.controlador;


import com.inventario.sistemainv.domain.User;
import com.inventario.sistemainv.domain.UserGroup;
import com.inventario.sistemainv.service.UserGroupService;
import com.inventario.sistemainv.service.UserDetailService;
import com.inventario.sistemainv.service.UserService;
import com.inventario.sistemainv.util.EncryptPass;
import com.inventario.sistemainv.web.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/grupos/editar_grupo/{id}")
    public String editarGrupo(@Validated UserGroup userGroup, Model model) {
        model.addAttribute("pageTitle", "Editar Grupo");
        log.info("Se va editar el grupo: " + userGroup);
        userGroup = userGroupService.searchGroup(userGroup);
        model.addAttribute("grupo", userGroup);
        return "edit_grupos";
    }

    @PostMapping("/grupos/add_grupo")
    public String agregarGrupo(@Validated UserGroup userGroup) {
        log.info("Se agregara :" + userGroup);
        try {
            userGroupService.saveGroup(userGroup);
            log.info("Agregado el grupo :" + userGroup);
            return "redirect:/accesos/grupos";
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR AL AGREGAR O MODIFICAR", e);
            return "accesos_grupos";
        }

    }


    //======================================USUARIOS=========================================
    @GetMapping("/usuarios")
    public String controlUsuarios(Model model,@AuthenticationPrincipal UserDetails user2auth) {
        model.addAttribute("pageTitle", "Accesos Usuarios");
        log.info("Acceso a usuarios");
        var username_logueado = user2auth.getUsername();
        model.addAttribute("username_logueado", username_logueado);
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
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR AL AGREGAR O MODIFICAR: "+e.getMessage());
            return "redirect:/accesos/usuarios";
        }

    }

    @GetMapping("/usuarios/edit_user/{id}")
    public String editarUsuario(@Validated User user, Model model) {
        model.addAttribute("pageTitle", "Editar Usuario");
        log.info("Se va editar el usuario: " + user);
        user = userService.searchUser(user);
        var grupos = userGroupService.listGroup();
        model.addAttribute("usuario", user);
        model.addAttribute("grupos", grupos);
        return "edit_usuarios";
    }

    @PostMapping("/usuarios/add_user/no_pass")
    public String agregarUsuarioNoPass(@Validated User user) {
        log.info("Se agregara :" + user);
        try {
            userService.saveUser(user);
            log.info("Agregado el usuario :" + user);
            return "redirect:/accesos/usuarios";
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR AL AGREGAR O MODIFICAR: "+e.getMessage());
            return "redirect:/accesos/usuarios";
        }
    }

    @PostMapping("/usuarios/add_user/pass")
    public String agregarUsuarioPass(@Validated User user) {
        User usuario = userService.searchUser(user);
        log.info("Se editara :" + usuario);
        log.info("Se agregara :" + user);
        if(user.getPassword()!=null || !user.getPassword().equals("")){
            usuario.setPassword(EncryptPass.Encriptar(user.getPassword()));
        }

        try {
            userService.saveUser(usuario);
            log.info("Agregado el usuario :" + usuario);
            return "redirect:/accesos/usuarios";
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR AL AGREGAR O MODIFICAR: "+e.getMessage());
            return "redirect:/accesos/usuarios";
        }
    }

    @GetMapping("/usuarios/eliminar_usuario/{id}")
    public String eliminarUsuario(User user) {
        log.info("Se elimino el usuario: " + user);
        userService.deleteUser(user);
        return "redirect:/accesos/usuarios";
    }

}
