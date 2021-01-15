package com.test.bakery.repository;

import com.test.bakery.model.Category;
import com.test.bakery.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    List<Status> findAll();
}
