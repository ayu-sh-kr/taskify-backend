package com.resotechs.services.business;

import com.resotechs.enums.RoleType;
import com.resotechs.repositories.TaskRepository;
import com.resotechs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ResponseEntity<?> generateStats(){
        try {
            Map<String, Number> map = new HashMap<>();
            map.put("totalAccount", userRepository.getAccountCount());
            map.put("activeAccount", userRepository.getActiveAccountCount());
            map.put("disabledAccount", userRepository.getDeactivateAccountCount());
            map.put("totalTask", taskRepository.getTaskCount());

            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllUserAccounts(){
        try {
            return new ResponseEntity<>(userRepository.findByRoleType(RoleType.USER), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
