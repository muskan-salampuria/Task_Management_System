package my.task_management.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.dao.TaskCollections;
import my.task_management.models.Task;
import my.task_management.utils.Constants;
import my.task_management.view.TaskScheduleImplementation;

public class TaskSchedule extends Task {
	private static final Logger LOGGER = LogManager.getLogger(TaskSchedule.class);

	TaskScheduleImplementation taskImplementationValidator = new TaskScheduleImplementation();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public void createNewTask() {

		Task task = new Task();
		task.setId(new TaskCollections().getTaskList().size() + 1);

		task.setTitle(taskImplementationValidator.titleInput());

		LocalDateTime startTime = taskImplementationValidator.startTimeInput();
		task.setStartTime(startTime);

		LocalDateTime endTime = taskImplementationValidator.endTimeInput(startTime);
		task.setEndTime(endTime);

		task.setStatus("PENDING");

		new TaskCollections().addTask(task);

		listsAllTasks();
		LOGGER.info("Task Added!!");
	}

	public void printTask(Task task) {
		LOGGER.info(Constants.LINES);
		String taskDetails = String.format("%10s | %15s | %20s | %20s | %15s | %15s", task.getId(), task.getTitle(),
				dateFormat.format(task.getStartTime()), dateFormat.format(task.getEndTime()), task.getStatus(),
				task.getNotesList());
		LOGGER.info(taskDetails);
		LOGGER.info(Constants.LINES);
	}

	public void listsAllTasks() {
		LOGGER.info(Constants.TASK_LIST);
		LOGGER.info(Constants.TABLE_HEADING);

		if (new TaskCollections().getTaskList().isEmpty()) {
			LOGGER.info(Constants.LINES);
			LOGGER.info(Constants.NO_TASKS);
			LOGGER.info(Constants.LINES);
		} else {
			for (Task task : new TaskCollections().getTaskList()) {
				printTask(task);
			}
		}
	}

	public void readTask() {
		long id = taskImplementationValidator.taskIDInput();
		boolean flag = false;
		for (Task task : new TaskCollections().getTaskList()) {
			if (task.getId() == id) {
				LOGGER.info(Constants.TASK_LIST);
				LOGGER.info(Constants.TABLE_HEADING);
				LOGGER.info("");
				printTask(task);
				flag = true;
				break;
			}
		}
		if (!flag)
			LOGGER.info(Constants.TASK_ID_NOT_FOUND);
	}

	public void modifyTask() {
		long id = taskImplementationValidator.taskIDInput();
		boolean found = false;
		for (Task task : new TaskCollections().getTaskList()) {
			if (task.getId() == id) {
				found = true;
				boolean flag = true;
				while (flag) {
					int choice = taskImplementationValidator.modifyChoiceInput();

					if (choice == 1)
						task.setTitle(taskImplementationValidator.titleInput());
					else if (choice == 2)
						task.setStartTime(taskImplementationValidator.startTimeInput(task.getEndTime()));
					else if (choice == 3)
						task.setEndTime(taskImplementationValidator.endTimeInput(task.getStartTime()));
					else if (choice == 4)
						task.setStatus(taskImplementationValidator.statusInput());
					else if (choice == 5) {
						flag = false;
					} else
						LOGGER.info(Constants.PLEASE_ENTER_A_VALID_CHOICE);
				}
				String result = "Task " + id + " Modified";
				LOGGER.info(result);
				break;
			}
		}
		if (!found)
			LOGGER.info(Constants.TASK_ID_NOT_FOUND);
	}

	public void deleteTasks() {
		long id = taskImplementationValidator.taskIDInput();
		if(!(new TaskCollections().getTaskList().stream().anyMatch(task -> task.getId() == id)))
			LOGGER.info(Constants.TASK_ID_NOT_FOUND);
		else {
			new TaskCollections().getTaskList().removeIf(task -> task.getId() == id);		
			String result = "Task " + id + " Deleted";
			LOGGER.info(result);
			}
		
//		boolean found = false;
//		for (Task task : new TaskCollections().getTaskList())
//			if (task.getId() == id) {
//				new TaskCollections().deleteTask(task);
//				String result = "Task " + id + " Deleted";
//				LOGGER.info(result);
//				found = true;
//				break;
//			}
//		if (!found)
//			LOGGER.info(Constants.TASK_ID_NOT_FOUND);
	}

	public void addNotes() {
		if (new TaskCollections().getTaskList().isEmpty()) {
			LOGGER.info(Constants.NO_TASKS);
			return;
		}
		int id = taskImplementationValidator.taskIDInput();
		if(!(new TaskCollections().getTaskList().stream().anyMatch(task -> task.getId() == id)))
			LOGGER.info(Constants.TASK_ID_NOT_FOUND);
		else
		{
			String note = taskImplementationValidator.noteInput();
			new TaskCollections().getTaskList().stream().filter(task-> task.getId()==id).findFirst().ifPresent(taskSchedule->taskSchedule.addNote(note));
		}
//		
//		for (Task task : new TaskCollections().getTaskList()) {
//			if (task.getId() == id) {
//				task.addNote(note);
//				String result = "For Task " + id + " Notes Added";
//				LOGGER.info(result);
//				found = true;
//				break;
//			}
//		}
//		if (!found)
//			LOGGER.info(Constants.TASK_ID_NOT_FOUND);
	}
}
