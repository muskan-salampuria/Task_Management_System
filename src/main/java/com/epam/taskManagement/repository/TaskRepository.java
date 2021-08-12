package com.epam.taskManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.taskManagement.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
