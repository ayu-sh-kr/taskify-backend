package com.resotechs.controllers;

import com.resotechs.dtos.StatusDto;
import com.resotechs.services.business.AdminService;
import com.resotechs.services.business.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardData(){
        return adminService.generateStats();
    }

    @PatchMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody StatusDto statusDto){
        return userService.updateAccountStatus(statusDto.getEmail(), statusDto.isStatus());
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(){
        return adminService.getAllUserAccounts();
    }
}
