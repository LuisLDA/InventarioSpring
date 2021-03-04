package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.UsersDao;
import com.inventario.sistemainv.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> listUser() {
        return (List<User>) usersDao.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        usersDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User searchUser(User user) {
        return usersDao.findById(user.getId()).orElse(null);
    }
}
