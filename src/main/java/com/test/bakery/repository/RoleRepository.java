package com.test.bakery.repository;

import com.test.bakery.model.Role;
import com.test.bakery.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * from role", nativeQuery=true)
    List<Role> getAllRoles();

    Role findByRoleName(String roleName);
}

//    @Query(value = "SELECT * from world.notes where ID = :id", nativeQuery=true)
//    List<Note> getNoteById(@Param("id") int id);