package com.test.bakery.controller;

import com.test.bakery.model.Status;
import com.test.bakery.repository.StatusRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class StatusController {
    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/statuses")
    public List<Status> getAllStatuses()
    {
    return statusRepository.findAll();
    }

}
