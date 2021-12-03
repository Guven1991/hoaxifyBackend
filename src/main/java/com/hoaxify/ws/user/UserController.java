package com.hoaxify.ws.user;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericResponse("user created");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Postman de (.OK---> 200 ok) yi 201 yapar
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }
}