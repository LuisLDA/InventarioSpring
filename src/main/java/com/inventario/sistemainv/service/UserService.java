package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.domain.User;

import java.util.List;

public interface UserService {
    List<User> listUser();
    void saveUser(User user);
    void deleteUser(User user);
    User searchUser(User user);
    User searchbyUserName(String username);

}
