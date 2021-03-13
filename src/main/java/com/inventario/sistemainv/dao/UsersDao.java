package com.inventario.sistemainv.dao;


import com.inventario.sistemainv.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsersDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
    //User findUserByUsername(String username);



}
