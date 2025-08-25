package com.jobportal.Job.Portal.Controller;


import com.jobportal.Job.Portal.JWT.AuthenticationRequest;
import com.jobportal.Job.Portal.JWT.AuthenticationResponse;
import com.jobportal.Job.Portal.JWT.CustomUserDetails;
import com.jobportal.Job.Portal.JWT.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")

public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        final UserDetails userDetails =userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtHelper.generateToken(userDetails);
        CustomUserDetails cud = (CustomUserDetails) userDetails;

        return ResponseEntity.ok(new AuthenticationResponse(jwt, cud.getId(), cud.getProfileId()));
    }




}
