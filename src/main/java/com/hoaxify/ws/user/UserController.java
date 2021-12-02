package com.hoaxify.ws.user;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    // UserController oluşturulurken @Autowired görecek
    // ve ona ihtiyacının oldugunu anlayıp objesini oluşturup aşağıya setleyecek
    // ve bunada dependency injection

    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/users")
    @ResponseStatus(HttpStatus.CREATED) //Postman de (.OK---> 200 ok) yi 201 yapar
    public ResponseEntity<?> createUser(@RequestBody User user){
        String username = user.getUsername();
if(username==null || username.isEmpty()){
    ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
    Map<String,String> validationErrors = new HashMap<>();
    validationErrors.put("username", "Username cannot be null");
    error.setValidationErrors(validationErrors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}
        userService.save(user);
        return ResponseEntity.ok( new GenericResponse("User Created"));
    }
}
