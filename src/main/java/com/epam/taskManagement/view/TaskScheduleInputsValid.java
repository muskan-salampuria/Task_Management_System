package com.epam.taskManagement.view;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.taskManagement.exception.StatusFormatException;
import com.epam.taskManagement.utils.Constants;
import com.epam.taskManagement.utils.Validations;

@Component
public class TaskScheduleInputsValid {

	private static final Logger LOGGER = LogManager.getLogger(TaskScheduleInputsValid.class);

	Scanner scan = new Scanner(System.in);
	Scanner scanLine = new Scanner(System.in);

	@Autowired
	Validations valid;

	public String titleInput() {
		String title = "";
		while (true) {
			try {
				LOGGER.info("Enter Title : ");
				title = scanLine.nextLine();
				if (title != "")
					break;
				else
					throw new NullPointerException("Title can not be NULL");
			} catch (NullPointerException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return (title);
	}

	public LocalDateTime startTimeInput() {
		boolean flag = true;
		LocalDateTime startTime = LocalDateTime.now();
		while (flag) {
			try {
				LOGGER.info(Constants.ENTER_START_DATE_TIME);
				startTime = valid.startDateValid(scanLine.nextLine());
				flag = false;
			} catch (Exception e) {
				LOGGER.error(Constants.RE_ENTER_THE_DATA);
			}
		}
		return startTime;
	}

	// Overload
	public LocalDateTime startTimeInput(LocalDateTime endTime) {
		boolean flag = true;
		LocalDateTime startTime = LocalDateTime.now();
		while (flag) {
			try {
				LOGGER.info(Constants.ENTER_START_DATE_TIME);
				startTime = valid.startDateValid(scan.nextLine(), endTime);
				flag = false;
			} catch (Exception e) {
				LOGGER.error(Constants.RE_ENTER_THE_DATA);
			}
		}
		return startTime;
	}

	public LocalDateTime endTimeInput(LocalDateTime startTime) {
		boolean flag = true;
		LocalDateTime endTime = LocalDateTime.now();
		while (flag) {
			try {
				LOGGER.info(Constants.ENTER_END_DATE_TIME);
				endTime = valid.endDateValid(startTime, scan.nextLine());
				flag = false;
			} catch (Exception e) {
				LOGGER.error(Constants.RE_ENTER_THE_DATA);
			}
		}
		return endTime;
	}

	public String statusInput() {
		boolean flag = true;
		String status = "";
		while (flag) {
			try {
				LOGGER.info(Constants.ENTER_THE_STATUS_COMPLETED_PENDING);
				status = valid.statusValid(scan.next());
				flag = false;
			} catch (NullPointerException | StatusFormatException e) {
				LOGGER.error(Constants.RE_ENTER_THE_DATA);
			}
		}
		return status.toUpperCase();
	}

	public String noteInput() {
		LOGGER.info(Constants.ENTER_NOTE);
		return scanLine.nextLine();
	}

	public int modifyChoiceInput() {
		boolean flag = true;
		int choice = 0;
		while (flag) {
			LOGGER.info(Constants.MODIFY_CHOICE_OPTIONS);
			try {
				choice = valid.choiceNumberValid(scan.next());
				flag = false;
			} catch (Exception e) {
				LOGGER.error(Constants.RE_ENTER_YOUR_CHOICE);
			}
		}
		return choice;
	}

	public int menuChoiceInput() {
		boolean flag = true;
		int choice = 0;
		while (flag) {
			LOGGER.info(Constants.CHOICE_MENU);
			try {
				choice = valid.choiceNumberValid(scan.next());
				flag = false;
			} catch (Exception e) {
				LOGGER.error(Constants.RE_ENTER_YOUR_CHOICE);
			}
		}
		return choice;
	}

	public int taskIDInput() {
		boolean flag = true;
		int taskId = 0;
		while (flag) {
			LOGGER.info("Enter Task ID : ");
			try {
				taskId = Integer.parseInt(scan.next());
				flag = false;
			} catch (NumberFormatException ndException) {
				LOGGER.error(Constants.RE_ENTER_THE_DATA);
			}
		}
		return taskId;
	}

	public int noteIDInput() {
		boolean flag = true;
		int noteId = 0;
		while (flag) {
			LOGGER.info("Enter Note ID : ");
			try {
				noteId = Integer.parseInt(scan.next());
				flag = false;
			} catch (NumberFormatException ndException) {
				LOGGER.error(Constants.RE_ENTER_THE_DATA);
			}
		}
		return noteId;
	}
}
