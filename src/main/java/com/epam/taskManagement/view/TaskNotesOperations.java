package com.epam.taskManagement.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.taskManagement.exception.DateTimeConflictException;
import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.NoteIDNotFoundException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Note;
import com.epam.taskManagement.models.Task;
import com.epam.taskManagement.service.NoteService;
import com.epam.taskManagement.service.TaskService;
import com.epam.taskManagement.utils.Constants;
import com.epam.taskManagement.utils.Validations;

@Component
public class TaskNotesOperations {

	private static final Logger LOGGER = LogManager.getLogger(TaskNotesOperations.class);
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Autowired
	TaskService taskService;
	@Autowired
	TaskScheduleInputsValid taskValidInputs;
	@Autowired
	NoteService noteService;
	@Autowired
	Validations validate;
	@Autowired
	ModifyTaskActions modifyTaskActions;

	public void createNewTask() {
		System.out.println("Create Method Calling");
		String title = taskValidInputs.titleInput();
		LocalDateTime startTime;
		LocalDateTime endTime;
		boolean flag = true;
		while (flag) {
			startTime = taskValidInputs.startTimeInput();
			endTime = taskValidInputs.endTimeInput(startTime);
			try {
				String result = "Task Created with ID : " + taskService.createNewTask(title, startTime, endTime);
				LOGGER.info(result);
				flag = false;
			} catch (DateTimeConflictException e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
			}
		}
	}

	public void listsAllTasks() {
		LOGGER.info(Constants.TASK_LIST);
		LOGGER.info(Constants.TASK_TABLE_HEADING);
		try {
			List<Task> allTask = taskService.listsAllTasks();
			for (Task task : allTask) {
				LOGGER.info(Constants.LINES);
				String taskDetails = String.format("%10s | %15s | %20s | %20s | %15s ", task.getId(), task.getTitle(),
						dateFormat.format(task.getStartTime()), dateFormat.format(task.getEndTime()), task.getStatus());
				LOGGER.info(taskDetails);
			}
			LOGGER.info(Constants.LINES);
		} catch (EmptyListException elException) {
			LOGGER.info(Constants.LINES);
			LOGGER.info(Constants.NO_TASKS);
			LOGGER.info(Constants.LINES);
		}
	}

	public void readTask() {
		int taskID = taskValidInputs.taskIDInput();
		try {
			Task task = taskService.readTask(taskID);
			LOGGER.info(Constants.TASK_LIST);
			LOGGER.info(Constants.TASK_TABLE_HEADING);
			String taskDetails = String.format("%10s | %15s | %20s | %20s | %15s ", task.getId(), task.getTitle(),
					dateFormat.format(task.getStartTime()), dateFormat.format(task.getEndTime()), task.getStatus());
			LOGGER.info(taskDetails);
		} catch (TaskIDNotFoundException tnfException) {
			LOGGER.error(tnfException.getMessage());
		}
	}

	public void modifyTask() {
		modifyTaskActions.executeMenu();
	}

	public void deleteTasks() {
		int id = taskValidInputs.taskIDInput();
		try {
			String result = "Task Deleted with taskID : " + taskService.deleteTasks(id);
			LOGGER.info(result);
		} catch (TaskIDNotFoundException tnfe) {
			LOGGER.error(tnfe.getMessage());
		}
	}

	public void addNotes() {
		int id = taskValidInputs.taskIDInput();
		String note = taskValidInputs.noteInput();
		try {
			String result = "Note Added for NoteID : " + noteService.addNotes(id, note).getId();
			LOGGER.info(result);
		} catch (TaskIDNotFoundException tnfe) {
			LOGGER.error(tnfe.getMessage());
		}
	}

	public void printNotes() {
		LOGGER.info(Constants.NOTES_LIST);
		LOGGER.info(Constants.NOTES_TABLE_HEADING);
		try {
			List<Note> notesList = noteService.printNotes();
			for (Note note : notesList) {
				LOGGER.info(Constants.LINES);
				String noteDetails = String.format("%10s | %20s | %10s ", note.getId(), note.getNotes(),
						note.getTask().getId());
				LOGGER.info(noteDetails);
			}
			LOGGER.info(Constants.LINES);
		} catch (EmptyListException ele) {
			LOGGER.info(Constants.LINES);
			LOGGER.info(Constants.NO_NOTES);
			LOGGER.info(Constants.LINES);
		}
	}

	public void modifyNote() {
		int noteID = taskValidInputs.noteIDInput();
		String noteInput = taskValidInputs.noteInput();
		try {
			String result = "Note Modified for NoteID : " + noteService.modifyNote(noteID, noteInput);
			LOGGER.info(result);
		} catch (NoteIDNotFoundException nidnfe) {
			LOGGER.error(nidnfe.getMessage());
		}
	}

	public void deleteNote() {
		int noteID = taskValidInputs.noteIDInput();
		try {
			String result = "Note Deleted with taskID : " + noteService.deleteNote(noteID);
			LOGGER.info(result);
		} catch (NoteIDNotFoundException nidnfe) {
			LOGGER.error(nidnfe.getMessage());
		}
	}
}
