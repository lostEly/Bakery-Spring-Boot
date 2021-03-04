//package com.test.bakery.service;
//
//import com.test.bakery.model.Userr;
//import com.test.bakery.model.VerificationToken;
//import com.test.bakery.repository.VerificationTokenRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VerificationTokenService {
//    private final VerificationTokenRepository verificationTokenRepository;
//
//    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
//        this.verificationTokenRepository = verificationTokenRepository;
//    }
//
//    public VerificationToken findByToken(String token)
//    {
//        return verificationTokenRepository.findByToken(token);
//    }
//
//    public VerificationToken findByUser(Userr userr)
//    {
//        return verificationTokenRepository.findByUserr(userr);
//    }
//
//}
