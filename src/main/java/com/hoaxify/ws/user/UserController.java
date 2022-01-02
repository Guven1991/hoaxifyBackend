package com.hoaxify.ws.user;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.shared.GenericResponse;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import com.hoaxify.ws.user.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0")
public class UserController {

    // UserController oluşturulurken @Autowired görecek
    // ve ona ihtiyacının oldugunu anlayıp objesini oluşturup aşağıya setleyecek
    // ve bunada dependency injection

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericResponse("user created");
    }
    @GetMapping("/users")
    Page<UserVM> getUser(Pageable page, @CurrentUser User user){
        return userService.getUsers(page, user).map(UserVM::new);
    }

    @GetMapping("/users/{username}")
    UserVM getUser(@PathVariable String username){
        User user = userService.getByUsername(username);
        return new UserVM(user);
    }

    @PutMapping("/users/{username}")
    @PreAuthorize("#username == principal.username")  // login olan kullanıcının kendisi dısındaki kullanıcıların bilgisini değiştirememesini sağlar
    UserVM updateUser(@Valid @RequestBody UserUpdateVM updatedUser, @PathVariable String username){
       User user = userService.updateUser(username, updatedUser);
        return new UserVM(user);
    }
}