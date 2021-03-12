package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.UserGroupDao;
import com.inventario.sistemainv.domain.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserGroupImpl implements UserGroupService {

    @Autowired
    private UserGroupDao usersGroupDao;

    @Override
    @Transactional(readOnly = true)
    public List<UserGroup> listGroup() {
        return (List<UserGroup>) usersGroupDao.findAll();
    }

    @Override
    @Transactional
    public void saveGroup(UserGroup userGroup) {
        usersGroupDao.save(userGroup);
    }

    @Override
    @Transactional
    public void deleteGroup(UserGroup userGroup) {
        usersGroupDao.delete(userGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public UserGroup searchGroup(UserGroup userGroup) {
        return usersGroupDao.findById(userGroup.getId()).orElse(null);
    }
}
