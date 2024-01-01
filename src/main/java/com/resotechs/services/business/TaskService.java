package com.resotechs.services.business;

import com.resotechs.dtos.TaskDto;
import com.resotechs.entities.Task;
import com.resotechs.entities.User;
import com.resotechs.enums.TaskStatus;
import com.resotechs.repositories.TaskRepository;
import com.resotechs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ResponseEntity<?> createTaskForUser(long userId, TaskDto taskDto){
        if(userRepository.existsById(userId)){
            User user = userRepository.getReferenceById(userId);
            Task task = TaskDto.getTaskFromDto(taskDto);
            task.setUser(user);
            taskRepository.save(task);
            return new ResponseEntity<>("Task Created Successfully", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("Invalid userId", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> getTasksFromUser(long userId){
        if(userRepository.existsById(userId)){
            return new ResponseEntity<>(taskRepository.findByUser(userId), HttpStatus.OK);
        }else
            return new ResponseEntity<>("Invalid userId", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteTaskByUser(long userId, long taskId){
        try{
            if(taskRepository.existsByIdAndUser_Id(taskId, userId)){
                taskRepository.deleteById(taskId);
                return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
            }else
                return new ResponseEntity<>("No such user or task exists", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> updateTaskById(long taskId, long userID, TaskDto taskDto){
        try {
            if(taskRepository.existsByIdAndUser_Id(taskId, userID)){
                int k = taskRepository.updateTitleAndDescriptionById(taskDto.getTitle(), taskDto.getDescription(), taskId);
                if(k > 0){
                    return new ResponseEntity<>("Task modified successfully", HttpStatus.OK);
                }else
                    return new ResponseEntity<>("Task can't be modified for unknown reason", HttpStatus.BAD_REQUEST);
            }else
                return new ResponseEntity<>("No such user or task exists", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> updateTitleById(long taskId, long userId, String title){
        try {
            if(taskRepository.existsByIdAndUser_Id(taskId, userId)){
                int k = taskRepository.updateTitleById(title, taskId);
                if(k > 0){
                    return new ResponseEntity<>("Task modified successfully", HttpStatus.OK);
                }else
                    return new ResponseEntity<>("Task can't be modified for unknown reason", HttpStatus.BAD_REQUEST);
            }else
                return new ResponseEntity<>("No such user or task exists", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> updateDescriptionById(long taskId, long userId, String description){
        try {
            if(taskRepository.existsByIdAndUser_Id(taskId, userId)){
                int k = taskRepository.updateDescriptionById(description, taskId);
                if(k > 0){
                    return new ResponseEntity<>("Task modified successfully", HttpStatus.OK);
                }else
                    return new ResponseEntity<>("Task can't be modified for unknown reason", HttpStatus.BAD_REQUEST);
            }else
                return new ResponseEntity<>("No such user or task exists", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateStatusById(long taskId, long userId, String status){
        try {
            if(taskRepository.existsByIdAndUser_Id(taskId, userId) && validStatus(status)){
                int k = taskRepository.updateStatusById(TaskStatus.valueOf(status), taskId);
                if(k > 0){
                    return new ResponseEntity<>("Status Updated Successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Fail to update Status", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validStatus(String status){
        try {
            TaskStatus.valueOf(status);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
