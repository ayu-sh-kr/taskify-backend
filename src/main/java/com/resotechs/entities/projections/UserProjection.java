package com.resotechs.entities.projections;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static com.resotechs.entities.projections.TaskProjection.*;

public class UserProjection {
    public interface UserInfo{

        @Value("#{target.id}")
        long getId();
        String getName();
        String getEmail();
        String getPhone();
        @Value("#{target.tasks.size()}")
        int getTasks();
    }

    public interface UserTaskInfo{
        long getId();
        String getEmail();
        List<TaskInfo> getTasks();
    }

    public interface UserAccountInfo{
        long getId();
        String getName();
        String getEmail();
        String getPhone();
        @Value("#{target.active}")
        boolean isActive();
    }
}
