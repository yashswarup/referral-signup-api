package com.example.referral.service;

import com.example.referral.model.User;
import com.example.referral.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        // Create a mock UserRepository
        userRepository = Mockito.mock(UserRepository.class);

        // Inject the mock into UserService
        userService = new UserService(userRepository);
    }

    @Test
    public void testCompleteProfile() {
        // Arrange: mock a User with profileCompleted = false
        User user = new User();
        user.setId(1L);
        user.setProfileCompleted(false);

        // Mock findById to return our user
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act: call the method under test
        userService.completeProfile(1L);

        // Assert: the profileCompleted flag should be true
        Assertions.assertTrue(user.isProfileCompleted());

        // Verify that save() was called exactly once with the updated user
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}
