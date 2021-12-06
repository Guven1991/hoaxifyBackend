package com.hoaxify.ws.auth;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/api/1.0/auth")
    ResponseEntity<Object> handleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization){
        if(authorization == null){
            ApiError error = new ApiError(401,"Unauthorized request", "/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String base64encoded =authorization.split("Basic ")[1]; // sfdgfhjjkukjlı=
        String decoded = new String(Base64.getDecoder().decode(base64encoded)); // user1:P4ssword
        String[] parts = decoded.split(":");
        String username = parts[0];
        String password =parts[1];
        User inDb =  userRepository.findByUsername(username);
        if(inDb == null){
            ApiError error = new ApiError(401,"Unauthorized request", "/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        }
        String hashedpassword = inDb.getPassword();
        if(!passwordEncoder.matches(password,hashedpassword)){
            ApiError error = new ApiError(401,"Unauthorized request", "/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        }

        return ResponseEntity.ok().build();
    }
}
