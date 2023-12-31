package com.resotechs.dtos;

import com.resotechs.entities.Task;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data @Builder
public class TaskDto {
    private String title;
    private String description;

    public static Task getTaskFromDto(TaskDto taskDto){
        Task task = new Task();
        if(StringUtils.isNotBlank(taskDto.getTitle())){
            task.setTitle(taskDto.getTitle());
        }else{
            throw new RuntimeException("Title is null");
        }

        if(StringUtils.isNotBlank(taskDto.getDescription())){
            task.setDescription(taskDto.getDescription());
        }else {
            throw new RuntimeException("Description is null");
        }
        return task;
    }
}
