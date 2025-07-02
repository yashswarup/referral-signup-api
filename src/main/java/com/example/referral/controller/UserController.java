
package com.example.referral.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.referral.dto.UserSignupRequest;
import com.example.referral.model.User;
import com.example.referral.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserSignupRequest request) {
        return ResponseEntity.ok(userService.signup(
            request.getName(),
            request.getEmail(),
            request.getPassword(),
            request.getReferralCode()
        ));
    }

    @PostMapping("/complete-profile/{id}")
public ResponseEntity<String> completeProfile(@PathVariable Long id) {
    userService.completeProfile(id);
    return ResponseEntity.ok("Profile marked as completed for user ID: " + id);
}

    @GetMapping("/referrals/{userId}")
public ResponseEntity<List<User>> getSuccessfulReferrals(@PathVariable Long userId) {
    List<User> referrals = userService.getSuccessfulReferrals(userId);
    return ResponseEntity.ok(referrals);
}

@GetMapping("/users")
public List<User> getAllUsers() {
    return userService.getAllUsers();
}


}