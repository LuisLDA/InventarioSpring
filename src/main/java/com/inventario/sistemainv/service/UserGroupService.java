package com.inventario.sistemainv.service;


import com.inventario.sistemainv.domain.UserGroup;

import java.util.List;

public interface UserGroupService {

    List<UserGroup> listGroup();
    void saveGroup(UserGroup userGroup);
    void deleteGroup(UserGroup userGroup);
    UserGroup searchGroup(UserGroup userGroup);
}
