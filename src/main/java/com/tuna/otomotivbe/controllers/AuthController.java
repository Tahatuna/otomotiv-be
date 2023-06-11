package com.tuna.otomotivbe.controllers;

import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.constants.ApiConstant;
import com.tuna.otomotivbe.payload.request.LoginRequest;
import com.tuna.otomotivbe.payload.request.SignupRequest;
import com.tuna.otomotivbe.payload.response.JwtResponse;
import com.tuna.otomotivbe.payload.response.MessageResponse;
import com.tuna.otomotivbe.security.jwt.JwtUtils;
import com.tuna.otomotivbe.security.userDetailServices.UserDetailsImpl;
import com.tuna.otomotivbe.services.IRoleService;
import com.tuna.otomotivbe.services.ISendVerificationEmail;
import com.tuna.otomotivbe.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = ApiConstant.END_POINT_AUTH)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ISendVerificationEmail sendVerificationEmail;

    @PostMapping(value = ApiConstant.END_POINT_SIGN_IN)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<User> user = userService.getByUserName(loginRequest.getUsername());

        if (user != null && user.get().isBlocked()) {
            userService.logFailedLoginAttempt(loginRequest.getUsername());
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User is blocked."));
        }
        if (user.get().isEmailVerified()) {
            Authentication authentication;
            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            } catch (AuthenticationException e) {
                userService.logFailedLoginAttempt(loginRequest.getUsername());
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid username or password."));
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("The account has not been activated, please check your email inbox."));
        }

    }

    @PostMapping(value = ApiConstant.END_POINT_SIGN_UP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String verificationToken = UUID.randomUUID().toString();
        boolean isEmailVerified = false;
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getUserFirstName(),
                signUpRequest.getUserLastName(),
                signUpRequest.getEmail(),
                isEmailVerified,
                verificationToken,
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.getRoleByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.getRoleByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleService.getRoleByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);
        sendVerificationEmail.mailSender(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully! Please check your email for confirmation."));
    }

    @GetMapping(value = ApiConstant.END_POINT_VERIFY)
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String verificationToken) {

        boolean verificationResult = userService.verifyMail(verificationToken);

        if (verificationResult) {
            return ResponseEntity.ok(new MessageResponse("Email verified successfully!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid verification token."));
        }
    }

}
