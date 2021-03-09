package com.inventario.sistemainv.dao;


import com.inventario.sistemainv.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersDao extends CrudRepository<User, Long> {


}
