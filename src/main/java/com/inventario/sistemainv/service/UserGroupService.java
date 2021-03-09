package com.inventario.sistemainv.service;


import com.inventario.sistemainv.domain.UserGroup;

import java.util.List;

public interface UserGroupService {

    public List<UserGroup> listGroup();
    public void saveGroup(UserGroup userGroup);
    public void deleteGroup(UserGroup userGroup);
    public UserGroup searchGroup(UserGroup userGroup);
}
