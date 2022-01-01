package com.hoaxify.ws.user;

import com.hoaxify.ws.File.FileService;
import com.hoaxify.ws.error.NotFoundException;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    FileService fileService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

    public void save(User user) {
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public Page<User> getUsers(Pageable page, User user) {
        if (user != null) {
            return userRepository.findByUsernameNot(user.getUsername(), page);
        }
        return userRepository.findAll(page);

    }

    public User getByUsername(String username) {
        User inDB = userRepository.findByUsername(username);
        if (inDB == null) {
            throw new NotFoundException();
        }
        return inDB;
    }

    public User updateUser(String username, UserUpdateVM updatedUser) {
        User inDB = getByUsername(username);
        inDB.setDisplayName(updatedUser.getDisplayName());
        if (updatedUser.getImage() != null) {
            String oldImageName = inDB.getImage();
            try {
                String storedFileName = fileService.writeBase64EncodedStringToFile(updatedUser.getImage());
                inDB.setImage(storedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.deleteFile(oldImageName);
        }
        return userRepository.save(inDB);
    }


}
