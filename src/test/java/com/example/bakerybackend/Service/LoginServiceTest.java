package com.example.bakerybackend.Service;

import com.example.bakerybackend.Entity.UserInfo;
import com.example.bakerybackend.Repository.UserRepository;
import com.example.bakerybackend.model.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {
    @Mock
    public UserRepository userRepo;

    @Mock
    public PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @InjectMocks
    public LoginService loginService;

    @Test
    public void addUserTest(){
        LoginRequest request = new LoginRequest();
        request.setUsername("testUser");
        request.setFirstName("Test");
        request.setLastName("User");
        request.setPassword("testUser123");
        request.setRole("USER");

        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepo.save(Mockito.any(UserInfo.class))).thenReturn(new UserInfo());

        ResponseEntity<?> response = loginService.addUser(request);

        assertEquals(ResponseEntity.ok("Success"), response);

        Mockito.verify(userRepo, Mockito.times(1)).save(Mockito.any(UserInfo.class));

    }
}
