package com.epam.taskManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.taskManagement.exception.DateTimeConflictException;
import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Task;

public interface TaskService {

	public abstract int createNewTask(String title, LocalDateTime startTime, LocalDateTime endTime) throws DateTimeConflictException;
	public abstract List<Task> listsAllTasks() throws EmptyListException;
	public abstract Task readTask(int taskID) throws TaskIDNotFoundException;
	public abstract String modifyTaskStatus(Task task, String status);
	public abstract int modifyTask(Task task);
	public abstract String modifyTaskStartTime(Task task, LocalDateTime startTime) throws DateTimeConflictException;
	public abstract String modifyTaskEndTime(Task task, LocalDateTime endTime)throws DateTimeConflictException;
	public abstract String deleteTasks(int taskID) throws TaskIDNotFoundException;
}

