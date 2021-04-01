package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserGroupDao extends JpaRepository<UserGroup,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users where user_level=:id",nativeQuery = true)
    void deleteAllByGroup_level(@Param("id") Long ID);
}
