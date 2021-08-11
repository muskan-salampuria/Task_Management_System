package com.epam.taskManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.taskManagement.dao.TaskNoteDAO;
import com.epam.taskManagement.exception.DateTimeConflictException;
import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.NoteIDNotFoundException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Note;
import com.epam.taskManagement.models.Task;
import com.epam.taskManagement.utils.Constants;

@Service
public class TaskNotesServiceImple implements TaskService,NoteService{
	
	@Autowired
	TaskNoteDAO taskScheduleDAO ;
	
	
	@Override
	public int createNewTask(String title, LocalDateTime startTime, LocalDateTime endTime) throws DateTimeConflictException {

		Task task=new Task();
		task.setTitle(title);
		task.setStatus(Constants.PENDING);
			if (isTimeConflict(startTime) || isTimeConflict(endTime) )
					throw new DateTimeConflictException();
			else {
					task.setStartTime(startTime);
					task.setEndTime(endTime);
			}
		return(taskScheduleDAO.createNewTask(task));
		}
	
	@Override
	public List<Task> listsAllTasks() throws EmptyListException {
		List<Task> allTask=taskScheduleDAO.listsAllTasks();
		if(allTask.isEmpty())
			throw new EmptyListException(Constants.LIST_IS_EMPTY);
		else
			return allTask;
	}
	
	@Override
	public Task readTask(int taskID) throws TaskIDNotFoundException {
		Task task=taskScheduleDAO.isTaskPresent(taskID);
		if(task==null)
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
		else {
			return task;
		}
	}
	@Override
	public String modifyTaskStatus(Task task, String status) {
		task.setStatus(status);
		int result = taskScheduleDAO.modifyTask(task);
		return("Status modified for Task :"+result);
	}
	
	@Override
	public int modifyTask(Task task) {
		int result = taskScheduleDAO.modifyTask(task);
		return(result);
	}
	
	@Override
	public String modifyTaskStartTime(Task task, LocalDateTime startTime)throws DateTimeConflictException {
		if (isTimeConflict(task.getId(),startTime))
			throw new DateTimeConflictException();
		else {
			task.setStartTime(startTime);
			int result =taskScheduleDAO.modifyTask(task);
			return ("StartTime Modified for Task : "+result);
		}
	}
	
	@Override
	public String modifyTaskEndTime(Task task, LocalDateTime endTime)throws DateTimeConflictException {
		if (isTimeConflict(task.getId(),endTime)  )
			throw new DateTimeConflictException();
		else {
			task.setEndTime(endTime);
			int result =taskScheduleDAO.modifyTask(task);
			return ("EndTime Modified for Task : "+result);
		}
	}
	
	@Override
	public String deleteTasks(int taskID) throws TaskIDNotFoundException {
		if(taskScheduleDAO.deleteTask(taskID))
			return("Task " + taskID + " Deleted");
		else
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
	}
	
	@Override
	public String addNotes(int taskID, String note) throws TaskIDNotFoundException {
		if(taskScheduleDAO.addNotes(taskID,note))
			return(Constants.NOTE_ADDED);
		else
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
	}
	
	@Override
	public List<Note> printNotes() throws EmptyListException {
		List<Note> notesList=taskScheduleDAO.listAllNotes();
		if (notesList.isEmpty()) {
			throw new EmptyListException("No Notes to Print");
		} else {
			return notesList;
		}
	}
	
	@Override
	public String modifyNote(int noteID, String noteInput) throws NoteIDNotFoundException {
		if(taskScheduleDAO.modifyNote(noteID,noteInput)) {
			String result = "Note " + noteID + " Modified";
			return(result);
		}
		else
			throw new NoteIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
	}
	
	@Override
	public String deleteNote(int noteID) throws NoteIDNotFoundException {
		if(taskScheduleDAO.deleteNote(noteID)) {
			String result = "Note " + noteID + " Deleted";
			return(result);
		}
		else
			throw new NoteIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
	}
	
	public boolean isTimeConflict(LocalDateTime dateTime) {
		List<Task> taskList=taskScheduleDAO.listsAllTasks();
		return taskList.stream()
				.anyMatch(task -> (task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
						|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime)));
	}
	
	//Overload
	public boolean isTimeConflict(int taskID, LocalDateTime dateTime) {
		List<Task> taskList=taskScheduleDAO.listsAllTasks();
		return taskList.stream()
				.anyMatch(task -> (task.getId()!=taskID) && ((task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
						|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime))));
	}
	
}
