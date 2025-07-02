package com.example.referral.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.referral.model.User;
import com.example.referral.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(String name, String email, String password, String referralCode) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        //user.setProfileCompleted(false);
        user.setReferralCode(generateReferralCode());

        if (referralCode != null && !referralCode.isEmpty()) {
            userRepository.findByReferralCode(referralCode).ifPresent(user::setReferrer);
        }

        return userRepository.save(user);
    }
    public void completeProfile(Long id) {
    try {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setProfileCompleted(true);
            userRepository.save(user);
            System.out.println("Profile marked completed for user ID: " + id);
        } else {
            System.out.println("User not found with ID: " + id);
            throw new RuntimeException("User not found with ID: " + id);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the actual exception
        throw new RuntimeException("Error while completing profile: " + e.getMessage());
    }
}



    public List<User> getSuccessfulReferrals(Long referrerId) {
    return userRepository.findByReferredByIdAndProfileCompletedTrue(referrerId);
}
    public List<User> getAllUsers() {
    return userRepository.findAll();
}



    private String generateReferralCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
