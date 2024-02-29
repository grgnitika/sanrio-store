package com.example.sanrio.Controller;

import com.example.sanrio.Entity.User;
import com.example.sanrio.Service.UserService;
import com.example.sanrio.Pojo.UserPojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("register")
@CrossOrigin(origins = "http://localhost:4004")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUsers(@RequestBody UserPojo userPojo) {
        try {
            userService.createUser(userPojo);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUsers(@RequestBody UserPojo loginPojo) {
        try {
            User loginMessage = userService.loginUser(loginPojo.getEmail(), loginPojo.getPassword());
            return new ResponseEntity<>(loginMessage, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllData() {
        List<User> allUsers = userService.getAllData();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody UserPojo userPojo) {
        try {
            // Check if new password and confirm password match
            String newPassword = userPojo.getPassword();
            String confirmPassword = userPojo.getConfirmPassword();

            if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
                throw new IllegalArgumentException("New password and confirm password do not match");
            }

            userService.resetPassword(userPojo.getEmail(), userPojo.getSecurityQuestion(), newPassword);
            return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
