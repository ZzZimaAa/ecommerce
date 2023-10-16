package com.coursework.ecommerce.service;

import com.coursework.ecommerce.dto.ResponseDto;
import com.coursework.ecommerce.dto.user.SignInDto;
import com.coursework.ecommerce.dto.user.SignInReponseDto;
import com.coursework.ecommerce.dto.user.SignupDto;
import com.coursework.ecommerce.exceptions.AuthenticationFailException;
import com.coursework.ecommerce.exceptions.CustomException;
import com.coursework.ecommerce.model.AuthenticationToken;
import com.coursework.ecommerce.model.User;
import com.coursework.ecommerce.model.UserProfile;
import com.coursework.ecommerce.model.UserRole;
import com.coursework.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // we have an user
            throw new CustomException("user already present");
        }


        // hash the password

        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDto.getEmail(), encryptedpassword);
        userRepository.save(user);

        // save the user


        //UserProfile userProfile = new UserProfile(user.getId(), signupDto.getFirstName(), signupDto.getLastName());

        //userProfileRepository.save(userProfile);

        //UserRole userRole = new UserRole(user.getId(), "user");

        //userRoleRepository.save(userRole);

        userRoleService.createUserRole(user);
        userProfileService.createUserProfile(signupDto, user);
        // create the token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created succesfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInReponseDto signIn(SignInDto signInDto) {
        // find user by email

        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }

        // hash the password

        try {
            if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // compare the password in DB

        // if password match

        AuthenticationToken token = authenticationService.getToken(user);

        // retrive the token

        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }

        return new SignInReponseDto("sucess", token.getToken());

        // return response
    }
}