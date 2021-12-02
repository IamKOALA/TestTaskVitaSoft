package com.company.VitaSoft.tasks.impl.repository;

import com.company.VitaSoft.tasks.impl.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM tasks where tasks.user_id = :user_id", nativeQuery = true)
    List<Task> findByUserId(@Param("user_id") Long userId);

    @Query(value = "SELECT * FROM tasks where tasks.status = 'SUBMITTED'", nativeQuery = true)
    List<Task> findSubmitted();
}
