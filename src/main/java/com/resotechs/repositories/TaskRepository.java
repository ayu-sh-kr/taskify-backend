package com.resotechs.repositories;

import com.resotechs.entities.Task;
import com.resotechs.entities.projections.TaskProjection;
import com.resotechs.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from task t where t.user.id = ?1")
    List<TaskProjection.TaskInfo> findByUser(long userId);

    @Transactional
    boolean existsByIdAndUser_Id(long taskId, long userId);

    @Transactional
    @Modifying
    @Query("update task t set t.title = ?1, t.description = ?2 where t.id = ?3")
    int updateTitleAndDescriptionById(String title, String description, long id);

    @Transactional
    @Modifying
    @Query("update task t set t.title = ?1 where t.id = ?2")
    int updateTitleById(String title, long id);

    @Transactional
    @Modifying
    @Query("update task t set t.description = ?1 where t.id = ?2")
    int updateDescriptionById(String description, long id);

    @Transactional
    @Modifying
    @Query("update task t set t.status = ?1 where t.id = ?2")
    int updateStatusById(TaskStatus status, long id);


}
