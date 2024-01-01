package com.resotechs.services.business;

import com.resotechs.dtos.UserDto;
import com.resotechs.entities.User;
import com.resotechs.repositories.TaskRepository;
import com.resotechs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<?> createAccount(UserDto userDto){
        try {
            if(StringUtils.isNotBlank(userDto.getEmail()) && !userRepository.existsByEmail(userDto.getEmail())){
                User user = UserDto.getUserFromDto(userDto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User with email already exists", HttpStatus.FORBIDDEN);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteAccount(String email){
        try {
            if (userRepository.existsByEmail(email)){
                userRepository.deleteByEmail(email);
                String builder = "Account with email: " +
                        email +
                        " deleted successfully";
                return new ResponseEntity<>(builder, HttpStatus.OK);
            }else return new ResponseEntity<>("No such user exist", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> disableAccount(String email){
        try {
            if(userRepository.existsByEmail(email)){
                userRepository.updateActiveByEmail(false, email);
                return new ResponseEntity<>("Account disabled", HttpStatus.OK);
            }else
                return new ResponseEntity<>("No such user exist", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> getAllUser(){
        try {
            return new ResponseEntity<>(userRepository.findAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> enableAccount(String email){
        try {
            if(userRepository.existsByEmail(email)){
                userRepository.updateActiveByEmail(true, email);
                return new ResponseEntity<>("Account enabled", HttpStatus.OK);
            }else
                return new ResponseEntity<>("No such user exists", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public long getUserIdByEmail(String email){
        if(userRepository.existsByEmail(email)){
            return userRepository.findByEmail(email).getId();
        }
        return -1;
    }
}
