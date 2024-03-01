package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.User;
import com.example.sanrio.Repo.UserRepo;
import com.example.sanrio.Pojo.UserPojo;
import com.example.sanrio.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    @Override
    public User createUser(UserPojo userPojo) {
        User user = new User();
        user.setEmail(userPojo.getEmail());
        user.setPassword(userPojo.getPassword());
        user.setConfirmPassword(userPojo.getConfirmPassword());
        user.setFullName(userPojo.getFullName());
        user.setSecurityQuestion(userPojo.getSecurityQuestion());
//        user.setRoles(user.getRoles());
        user.setRoles("Customer");
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllData() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }


//    @Override
//    public User loginUser(String email, String password) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            if (password.equals(user.getPassword())) {
//                return user;
//            }
//        }
//        throw new IllegalArgumentException("Invalid password or email");
//    }

    @Override
    public User loginUser(String email, String password) {
        if (email.equals("admin@gmail.com") && password.equals("admin123")) {
            // Admin login logic
            User admin = new User();
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setFullName("Admin");
            admin.setSecurityQuestion("ADMIN IS GREAT");  // You can set a default security question for admin
            admin.setRoles("ADMIN");
            return admin;
        } else {
            // User login logic
            Optional<User> optionalUser = userRepository.findByEmail(email);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                if (password.equals(user.getPassword())) {
                    return user;
                }
            }

            throw new IllegalArgumentException("Invalid password or email");
        }
    }

    public void resetPassword(String email, String securityQuestion, String newPassword) {
        User user = userRepository.findByEmailAndSecurityQuestion(email, securityQuestion)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or security question"));

        // Update the user's password directly
        user.setPassword(newPassword);

        // Save the updated user entity
        userRepository.save(user);
    }



}
