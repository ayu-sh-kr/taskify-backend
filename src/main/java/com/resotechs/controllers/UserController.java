package com.resotechs.controllers;

import com.resotechs.services.business.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String email){
        return userService.deleteAccount(email);
    }

    @PatchMapping("/disable")
    public ResponseEntity<?> disableUser(@RequestBody String email){
        return userService.disableAccount(email);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(){
        return userService.getAllUser();
    }

    @PatchMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestBody String email){
        return userService.enableAccount(email);
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getProfileData(@RequestParam("userId") long id){
        return userService.getUserDetails(id);
    }
}
