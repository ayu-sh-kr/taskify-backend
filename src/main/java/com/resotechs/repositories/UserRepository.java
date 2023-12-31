package com.resotechs.repositories;

import com.resotechs.entities.Task;
import com.resotechs.entities.User;
import com.resotechs.entities.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("update user_details u set u.active = ?1 where u.email = ?2")
    void updateActiveByEmail(boolean active, String email);

    @Query("select u.tasks from user_details u where u.id = ?1")
    List<Task> findAllTasksById(long id);

    @Query("select u from user_details u")
    List<UserProjection.UserInfo> findAllUsers();

}
