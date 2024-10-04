package com.blog.authentication;

import com.blog.models.Users;
import com.blog.services.UserService; // Import UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService; // Inject UserService

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Use your custom Users class instead of Spring's User class
    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user); // Save user using UserService
        return "User registered successfully!";
    }

    //login function
    @PostMapping("/login")
    public String createToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername()); // Use UserService
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return jwt;
    }
}
