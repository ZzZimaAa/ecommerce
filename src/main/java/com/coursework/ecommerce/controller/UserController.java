package com.coursework.ecommerce.controller;


import com.coursework.ecommerce.dto.*;
import com.coursework.ecommerce.dto.user.SignInDto;
import com.coursework.ecommerce.dto.user.SignInReponseDto;
import com.coursework.ecommerce.dto.user.SignupDto;
import com.coursework.ecommerce.exceptions.AuthenticationFailException;
import com.coursework.ecommerce.exceptions.CustomException;
import com.coursework.ecommerce.model.User;
import com.coursework.ecommerce.model.UserRole;
import com.coursework.ecommerce.repository.UserRepository;
import com.coursework.ecommerce.service.AuthenticationService;
import com.coursework.ecommerce.service.UserProfileService;
import com.coursework.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInReponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
        return userService.signIn(signInDto);
    }

    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(@RequestHeader("Authorization") String token) {
        try {
            // Authenticate the user based on the token (you might need a method for this)
            User authenticatedUser = authenticationService.getUser(token);

            // Get the user's role from the authenticated user
            UserRole userRole = userProfileService.getUserProf(authenticatedUser);

            if (userRole != null) {
                // Return the user's role
                return ResponseEntity.ok(userRole.getRoleName());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User role not found");
            }
        } catch (Exception e) {
            // Handle authentication or other exceptions
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

//    @PostMapping("/updateUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserUpdateDto userUpdateDto) {
//        authenticationService.authenticate(token);
//        return userService.updateUser(token, userUpdateDto);
//    }


//    @PostMapping("/createUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
//            throws CustomException, AuthenticationFailException {
//        authenticationService.authenticate(token);
//        return userService.createUser(token, userCreateDto);
//    }
    }
}