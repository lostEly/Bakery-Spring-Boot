package com.test.bakery.repository;

import com.test.bakery.model.Category;
import com.test.bakery.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    List<Status> findAll();
    Optional<Status> findByStatusName(String statusName);
}
