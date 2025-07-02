
package com.example.referral.controller;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

    public UserController(UserService userService) {
    this.userService = userService;
}


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

@GetMapping("/report")
public ResponseEntity<Resource> downloadReferralReport() throws IOException {
    String filename = "referral_report.csv";
    StringBuilder csvBuilder = new StringBuilder();

    // Add CSV headers
    csvBuilder.append("UserID,UserName,Email,ReferralCode,ReferredUsers\n");

    // Fetch all users
    List<User> users = userService.getAllUsers();

    for (User user : users) {
        StringBuilder referredUsernames = new StringBuilder();
        List<User> referredUsers = userService.getReferrals(user.getId());

        for (int i = 0; i < referredUsers.size(); i++) {
            referredUsernames.append(referredUsers.get(i).getName());
            if (i != referredUsers.size() - 1) {
                referredUsernames.append(" | ");
            }
        }

        csvBuilder.append(user.getId()).append(",");
        csvBuilder.append(user.getName()).append(",");
        csvBuilder.append(user.getEmail()).append(",");
        csvBuilder.append(user.getReferralCode()).append(",");
        csvBuilder.append("\"").append(referredUsernames).append("\"\n");
    }

    byte[] csvBytes = csvBuilder.toString().getBytes(StandardCharsets.UTF_8);
    ByteArrayResource resource = new ByteArrayResource(csvBytes);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(resource);
}

}