package com.resotechs.entities.projections;

import org.springframework.beans.factory.annotation.Value;

public class TaskProjection {
    public interface TaskInfo{

        @Value("#{target.id}")
        long getId();

        @Value("#{target.title}")
        String getTitle();

        @Value("#{target.status.name()}")
        String getStatus();

        @Value("#{target.description}")
        String getDescription();
    }
}
