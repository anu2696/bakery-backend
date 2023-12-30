package com.example.bakerybackend.Service;

import com.example.bakerybackend.Entity.UserInfo;
import com.example.bakerybackend.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceImplTest {

    @Mock
    public UserRepository userRepo;
    @InjectMocks
    public UserDetailsServiceImpl userDetailsServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        String username = "testUser";
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword("password");
        userInfo.setRole("User");
        Mockito.when(userRepo.findByUsername(username)).thenReturn(userInfo);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        String nonUsername = "nonUserName";
        Mockito.when(userRepo.findByUsername(nonUsername)).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, ()-> userDetailsServiceImpl.loadUserByUsername(nonUsername));
    }
}
