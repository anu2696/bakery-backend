package com.example.bakerybackend.Service;

import com.example.bakerybackend.Entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomUserDetailsTest {
    @InjectMocks
    public CustomUserDetails customUserDetails;

    @Test
    void testCustomUserDetails(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("testUser");
        userInfo.setPassword("testPassword");
        userInfo.setRole("USER");

        CustomUserDetails customUserDetails = new CustomUserDetails(userInfo);
        assertEquals("testUser", customUserDetails.getUsername());
        assertEquals("testPassword", customUserDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));

        assertTrue(customUserDetails.isAccountNonExpired());
        assertTrue(customUserDetails.isAccountNonLocked());
        assertTrue(customUserDetails.isEnabled());
        assertTrue(customUserDetails.isCredentialsNonExpired());

    }
}
