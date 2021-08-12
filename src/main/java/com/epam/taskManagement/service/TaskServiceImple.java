package com.epam.taskManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.taskManagement.exception.DateTimeConflictException;
import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Task;
import com.epam.taskManagement.repository.TaskRepository;
import com.epam.taskManagement.utils.Constants;

@Service
public class TaskServiceImple implements TaskService {

	@Autowired
	TaskRepository taskRepo;

	@Override
	public Task createNewTask(String title, LocalDateTime startTime, LocalDateTime endTime)
			throws DateTimeConflictException {

		Task task = new Task();
		task.setTitle(title);
		task.setStatus(Constants.PENDING);
		if (isTimeConflict(startTime) || isTimeConflict(endTime))
			throw new DateTimeConflictException();
		else {
			task.setStartTime(startTime);
			task.setEndTime(endTime);
		}
		return (taskRepo.save(task));
	}

	@Override
	public List<Task> listsAllTasks() throws EmptyListException {
		List<Task> allTask = taskRepo.findAll();
		if (allTask.isEmpty())
			throw new EmptyListException(Constants.LIST_IS_EMPTY);
		else
			return allTask;
	}

	@Override
	public Task readTask(int taskID) throws TaskIDNotFoundException {
		Optional<Task> task = taskRepo.findById(taskID);
		if (task == null)
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
		return task.get();
	}

	@Override
	public int modifyTaskStatus(Task task, String status) {
		task.setStatus(status);
		taskRepo.save(task);
		return (task.getId());
	}

	@Override
	public int modifyTaskTitle(Task task, String title) {
		task.setTitle(title);
		taskRepo.save(task);
		return (task.getId());
	}

	@Override
	public int modifyTaskStartTime(Task task, LocalDateTime startTime) throws DateTimeConflictException {
		if (isTimeConflict(task.getId(), startTime))
			throw new DateTimeConflictException();
		task.setStartTime(startTime);
		taskRepo.save(task);
		return (task.getId());
	}

	@Override
	public int modifyTaskEndTime(Task task, LocalDateTime endTime) throws DateTimeConflictException {
		if (isTimeConflict(task.getId(), endTime))
			throw new DateTimeConflictException();
		task.setEndTime(endTime);
		taskRepo.save(task);
		return (task.getId());
	}

	@Override
	public int deleteTasks(int taskID) throws TaskIDNotFoundException {
		Task task = taskRepo.findById(taskID).get();
		if (Objects.isNull(task))
			throw new TaskIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
		taskRepo.deleteById(taskID);
		return taskID;
	}

	public boolean isTimeConflict(LocalDateTime dateTime) {
		List<Task> taskList = taskRepo.findAll();
		return taskList.stream()
				.anyMatch(task -> (task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
						|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime)));
	}

	// Overload
	public boolean isTimeConflict(int taskID, LocalDateTime dateTime) {
		List<Task> taskList = taskRepo.findAll();
		return taskList.stream()
				.anyMatch(task -> (task.getId() != taskID)
						&& ((task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
								|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime))));
	}

}
