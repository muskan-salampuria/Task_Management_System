package my.task_management.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import my.task_management.dao.TaskScheduleDAO;
import my.task_management.exception.DateTimeConflictException;
import my.task_management.exception.EmptyListException;
import my.task_management.exception.TaskIDNotFoundException;
import my.task_management.models.Task;
import my.task_management.utils.Constants;
import my.task_management.utils.Validations;

public class TaskSchedule{

	TaskScheduleDAO taskScheduleDAO = new TaskScheduleDAO();
	Validations validate = new Validations();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	

	public String createNewTask(String title, LocalDateTime startTime, LocalDateTime endTime) throws DateTimeConflictException {

		Task task=new Task();
		task.setTitle(title);
		task.setStatus(Constants.PENDING);
			if (validate.timeConflict(startTime) || validate.timeConflict(endTime) )
					throw new DateTimeConflictException(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
			else {
					task.setStartTime(startTime);
					task.setEndTime(endTime);
					return(taskScheduleDAO.createNewTask(task));
			}
		}

	public List<Task> listsAllTasks() throws EmptyListException {
		List<Task> allTask=taskScheduleDAO.listsAllTasks();
		if(allTask.isEmpty())
			throw new EmptyListException(Constants.LIST_IS_EMPTY);
		else
			return allTask;
	}

	public Task readTask(int taskID) throws TaskIDNotFoundException {
		Task task=taskScheduleDAO.isTaskPresent(taskID);
		if(task==null)
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
		else {
			return task;
		}
	}

	public String modifyTaskStatus(Task task, String status) {
		task.setStatus(status);
		int result = taskScheduleDAO.modifyTask(task);
		return("Status modified for Task :"+result);
	}
	
	public String modifyTaskTitle(Task task,String title) {
		task.setTitle(title);
		int result = taskScheduleDAO.modifyTask(task);
		return("Title modified for Task :"+result);
	}
	
	public String modifyTaskStartTime(Task task, LocalDateTime startTime)throws DateTimeConflictException {
		if (validate.timeConflict(task.getId(),startTime)  )
			throw new DateTimeConflictException(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
		else {
			task.setStartTime(startTime);
			int result =taskScheduleDAO.modifyTask(task);
			return ("StartTime Modified for Task : "+result);
		}
	}
	
	public String modifyTaskEndTime(Task task, LocalDateTime endTime)throws DateTimeConflictException {
		if (validate.timeConflict(task.getId(),endTime)  )
			throw new DateTimeConflictException(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
		else {
			task.setEndTime(endTime);
			int result =taskScheduleDAO.modifyTask(task);
			return ("EndTime Modified for Task : "+result);
		}
	}
	
	public String deleteTasks(int taskID) throws TaskIDNotFoundException {
		if(taskScheduleDAO.deleteTask(taskID))
			return("Task " + taskID + " Deleted");
		else
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
	}
}
