package com.example.studentsystem.service;

import com.example.studentsystem.exception.UserNotFoundException;
import com.example.studentsystem.model.User;
import com.example.studentsystem.model.enums.Role;
import com.example.studentsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(email)!=null) return false;
        System.out.println(user);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        System.out.println("created");
        return true;
    }

    public List<User> findeAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) throws UserNotFoundException {
        Optional<User> opt_user = userRepository.findById(userId);
        if(opt_user.isPresent()){
            return opt_user.get();
        }
        else
            throw new UserNotFoundException("user with id '"+userId+"' not found");
    }

    public void updateUser(User user, Long userId) throws UserNotFoundException {
        Optional<User> opt_user = userRepository.findById(userId);
        if(opt_user.isPresent()){
            if(user.getEmail().equals("")){
                opt_user.get().setEmail(opt_user.get().getEmail());
            }
            else
                opt_user.get().setEmail(user.getEmail());
            if (user.getName().equals("")) {
                opt_user.get().setName(opt_user.get().getName());
            }
            else
                opt_user.get().setName(user.getName());

            userRepository.save(opt_user.get());

        }
        else
            throw new UserNotFoundException("user with id '"+userId+"' not found");
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        Optional<User> opt_user = userRepository.findById(userId);
        if(opt_user.isPresent()){
            userRepository.delete(opt_user.get());
        }
        else
            throw new UserNotFoundException("user with id '"+userId+"'not found ");


    }
}
