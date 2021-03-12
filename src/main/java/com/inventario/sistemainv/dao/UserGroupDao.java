package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupDao extends JpaRepository<UserGroup,Long> {

}
