package com.hoaxify.ws.user;

import com.hoaxify.ws.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
        userService.save(user);
        return ResponseEntity.ok( new GenericResponse("User Created"));
    }
}
