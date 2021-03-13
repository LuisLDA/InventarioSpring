package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.domain.User;

import java.util.List;

public interface UserService {
    public List<User> listUser();
    public void saveUser(User user);
    public void deleteUser(User user);
    public User searchUser(User user);
    public User searchbyUserName(String  username);

}
