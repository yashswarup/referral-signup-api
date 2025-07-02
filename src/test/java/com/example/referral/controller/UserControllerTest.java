package com.example.referral.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.http.ResponseEntity;

import com.example.referral.service.UserService;

public class UserControllerTest {

    @Test
    public void testCompleteProfileEndpoint() {
        // Arrange: Mock the UserService
        UserService mockUserService = Mockito.mock(UserService.class);
        UserController userController = new UserController(mockUserService);

        // Mock the service method call
        doNothing().when(mockUserService).completeProfile(1L);

        // Act: call the controller method
        ResponseEntity<String> responseEntity = userController.completeProfile(1L);
        String response = responseEntity.getBody();


        // Assert
        assertEquals("Profile marked as completed for user ID: 1", response);


        // Verify service call
        verify(mockUserService, times(1)).completeProfile(1L);
    }
}
