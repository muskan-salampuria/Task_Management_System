package com.epam.taskManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.taskManagement.exception.DateTimeConflictException;
import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Task;

public interface TaskService {

	public abstract Task createNewTask(String title, LocalDateTime startTime, LocalDateTime endTime)
			throws DateTimeConflictException;

	public abstract List<Task> listsAllTasks() throws EmptyListException;

	public abstract Task readTask(int taskID) throws TaskIDNotFoundException;

	public abstract int modifyTaskStatus(Task task, String status);

	public abstract int modifyTaskTitle(Task task, String title);

	public abstract int modifyTaskStartTime(Task task, LocalDateTime startTime) throws DateTimeConflictException;

	public abstract int modifyTaskEndTime(Task task, LocalDateTime endTime) throws DateTimeConflictException;

	public abstract int deleteTasks(int taskID) throws TaskIDNotFoundException;
}
