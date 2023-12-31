package com.resotechs.controllers;

import com.resotechs.dtos.LoginRequest;
import com.resotechs.dtos.UserDto;
import com.resotechs.services.business.UserService;
import com.resotechs.services.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        if(null != loginRequest){
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            try {
                Authentication res = authenticationManager.authenticate(authentication);
                if(res.isAuthenticated()){
                    Map<String, String> map = new HashMap<>();
                    String token = tokenService.generateToken(res);
                    map.put("jwt", token);
                    return new ResponseEntity<>(map, HttpStatus.OK);
                }
                return new ResponseEntity<>("Username password did not match", HttpStatus.UNAUTHORIZED);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Malformed Request", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        return userService.createAccount(userDto);
    }
}
