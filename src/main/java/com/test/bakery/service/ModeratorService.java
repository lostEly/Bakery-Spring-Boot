package com.test.bakery.service;

import com.test.bakery.repository.UserrRepository;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService {
    UserrRepository userRepository;

    public ModeratorService(UserrRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Userr findByUserrId(Long id)
//    {
//        return userRepository.findByUserrId(id);
//    }
//
//
//    public Event SaveEvent(Event event)
//    {
//
//    }


}
