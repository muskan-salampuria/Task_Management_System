package com.epam.taskManagement.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.taskManagement.exception.DateTimeConflictException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Task;
import com.epam.taskManagement.service.TaskService;
import com.epam.taskManagement.utils.Constants;
import com.epam.taskManagement.utils.Validations;

@Component
public class ModifyTaskActions {

	private static final Logger LOGGER = LogManager.getLogger(ModifyTaskActions.class);
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Autowired
	TaskService taskService;
	@Autowired
	TaskScheduleInputsValid taskValidInputs;
	@Autowired
	Validations validate;

	public void executeMenu() {
		int taskID = taskValidInputs.taskIDInput();
		try {
			Task task = taskService.readTask(taskID);
			int choice;
			boolean processRunning = true;
			while (processRunning) {
				choice = taskValidInputs.modifyChoiceInput();
				switch (choice) {
				case 1:
					modifyTitle(task);
					break;
				case 2:
					modifyStartTime(task);
					break;
				case 3:
					modifyEndTime(task);
					break;
				case 4:
					modifyStatus(task);
					break;
				case 5:
					processRunning = false;
					break;
				default:
					LOGGER.info(Constants.PLEASE_ENTER_A_VALID_CHOICE);
				}
			}
		} catch (TaskIDNotFoundException tnfException) {
			LOGGER.error(tnfException.getMessage());
		}
	}

	private void modifyStatus(Task task) {
		String result;
		String status = taskValidInputs.statusInput();

		result = "Status Modified for Task : " + taskService.modifyTaskStatus(task, status);
		LOGGER.info(result);
	}

	private void modifyEndTime(Task task) {
		String result;
		LocalDateTime endTime;
		while (true) {
			endTime = taskValidInputs.endTimeInput(task.getStartTime());
			try {
				result = "EndTime Modified for Task : " + taskService.modifyTaskEndTime(task, endTime);
				LOGGER.info(result);
				break;
			} catch (DateTimeConflictException e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
			}
		}
	}

	private void modifyStartTime(Task task) {
		String result;
		LocalDateTime startTime;
		while (true) {
			startTime = taskValidInputs.startTimeInput(task.getEndTime());
			try {
				result = "StartTime Modified for Task : " + taskService.modifyTaskStartTime(task, startTime);
				LOGGER.info(result);
				break;
			} catch (DateTimeConflictException e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
			}
		}
	}

	private void modifyTitle(Task task) {
		String result;
		String title = taskValidInputs.titleInput();
		result = "Title Modified for :" + taskService.modifyTaskTitle(task, title);
		LOGGER.info(result);
	}
}
