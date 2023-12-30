package com.example.bakerybackend.Controller;

import com.example.bakerybackend.Entity.UserInfo;
import com.example.bakerybackend.Repository.UserRepository;
import com.example.bakerybackend.Service.JwtService;
import com.example.bakerybackend.Service.LoginService;
import com.example.bakerybackend.Service.UserDetailsServiceImpl;
import com.example.bakerybackend.model.AuthRequest;
import com.example.bakerybackend.model.JwtResponse;
import com.example.bakerybackend.model.LoginRequest;
import com.example.bakerybackend.model.LoginResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private final AuthenticationManager authenticationManager;
    @Autowired
    public JwtService jwtService;

    @Autowired
    UserRepository userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @PostMapping(value="/loginAuth")
    public ResponseEntity<LoginResponseWrapper> AuthenticatesAndGetToken(@RequestBody AuthRequest request) throws Exception{
        logger.info("In controller method");
        LoginResponseWrapper wrapper = new LoginResponseWrapper();
        JwtResponse response = new JwtResponse();
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            if (authentication.isAuthenticated()) {
                response = JwtResponse.builder().accessToken(jwtService.generateToken(request.getUsername())).build();
            } else {
                throw new UsernameNotFoundException("Invalid user request..!!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        wrapper.setAccessToken(String.valueOf(response));
        wrapper.setUsername(request.getUsername());
        UserInfo user = userRepo.findByUsername(request.getUsername());
        wrapper.setFirstName(user.getFirstName());
        wrapper.setLastName(user.getLastName());
        wrapper.setRole(user.getRole());
        return new ResponseEntity<LoginResponseWrapper>(wrapper,HttpStatus.OK);
    }

    @PostMapping(value="/signUp")
    public ResponseEntity<?> addUserController(@RequestBody LoginRequest request){
        return loginService.addUser(request);
    }
}
