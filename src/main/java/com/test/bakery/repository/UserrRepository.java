package com.test.bakery.repository;

import com.test.bakery.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserrRepository extends JpaRepository<Userr, Long> {
    @Query(value="SELECT * from userr", nativeQuery = true)
    List<Userr> getAllUsers();

    Userr findByLogin(String login);

    @Query(value="select * from userr where userr_id = :id", nativeQuery=true)
    Userr findByUserrId(@Param("id") Long id);

}
