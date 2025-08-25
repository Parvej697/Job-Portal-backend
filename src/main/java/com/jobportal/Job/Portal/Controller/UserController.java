package com.jobportal.Job.Portal.Controller;

import com.jobportal.Job.Portal.DTO.LoginDTO;
import com.jobportal.Job.Portal.DTO.ResponseDTO;
import com.jobportal.Job.Portal.DTO.UserDTO;
import com.jobportal.Job.Portal.Exception.JobPortalException;
import com.jobportal.Job.Portal.JWT.AuthenticationResponse;
import com.jobportal.Job.Portal.JWT.CustomUserDetails;
import com.jobportal.Job.Portal.JWT.JwtHelper;
import com.jobportal.Job.Portal.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException{
            userDTO = userService.registerUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        final String jwt = jwtHelper.generateToken(userDetails);
        CustomUserDetails cud = (CustomUserDetails) userDetails;

        return ResponseEntity.ok(new AuthenticationResponse(jwt, cud.getId(), cud.getProfileId()));
    }


    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email) throws Exception{
          userService.sendOtp(email);
            return new ResponseEntity<>(new ResponseDTO("OTP sent successfully."), HttpStatus.CREATED);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable @Email(message = "{user.email.invalid}") String email,@PathVariable @Pattern(regexp="^[0-9]{6}$", message="{otp.invalid}") String otp) throws JobPortalException{
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDTO("OTP has been verified."), HttpStatus.OK);
    }

    @PostMapping("/changePass")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(loginDTO), HttpStatus.OK);
    }
}
