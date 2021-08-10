package my.task_management.view;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.utils.Constants;
import my.task_management.utils.Validations;

public class TaskScheduleInputsValid {

	private static final Logger LOGGER= LogManager.getLogger(TaskScheduleInputsValid.class);
	
	Scanner scan = new Scanner(System.in);
	Scanner scanLine = new Scanner(System.in);
	Validations valid = new Validations();

	public String titleInput() {
		LOGGER.info("Enter Title : ");
		return(scanLine.nextLine());
	}

	public LocalDateTime startTimeInput() {
		boolean flag = true;
		LocalDateTime startTime = LocalDateTime.now();
		while (flag) {
			try {
				LOGGER.info(Constants.ENTER_START_DATE_TIME);
				startTime = new Validations().startDateValid(scanLine.nextLine());
				flag = false;
			} catch (Exception e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
			}
		}
		return startTime;
	}

	//Overload
	public LocalDateTime startTimeInput(LocalDateTime endTime) {
		boolean flag = true;
		LocalDateTime startTime = LocalDateTime.now();
		while (flag) {
			try {
				LOGGER.info(Constants.ENTER_START_DATE_TIME);
				startTime = new Validations().startDateValid(scan.nextLine(), endTime);
				flag = false;
			} 
			catch (Exception e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
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
				endTime = new Validations().endDateValid(startTime, scan.nextLine());
				flag = false;
			} 
			catch (Exception e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
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
				status = new Validations().statusValid(scan.next());
				flag = false;
			} catch (Exception e) {
				LOGGER.warn(Constants.RE_ENTER_THE_DATA);
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
				choice = new Validations().choiceValid(scan.next());
				flag = false;
			} catch (Exception e) {
				LOGGER.info(Constants.RE_ENTER_YOUR_CHOICE);
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
				choice = new Validations().choiceValid(scan.next());
				flag = false;
			} catch (Exception e) {
				LOGGER.warn(Constants.RE_ENTER_YOUR_CHOICE);
			}
		}
		return choice;
	}

	public int taskIDInput() {
		boolean flag = true;
		int taskId=0;
		while (flag) {
			LOGGER.info("Enter Task ID : ");
			try {
				taskId = Integer.parseInt(scan.next());
				flag=false;
			}catch(NumberFormatException ndException) {
				LOGGER.info(Constants.RE_ENTER_THE_DATA);
			}
		}
		return taskId;
	}

	public int noteIDInput() {
		boolean flag = true;
		int noteId=0;
		while (flag) {
			LOGGER.info("Enter Note ID : ");
			try {
				noteId = Integer.parseInt(scan.next());
				flag=false;
			}catch(NumberFormatException ndException) {
				LOGGER.info(Constants.RE_ENTER_THE_DATA);
			}
		}
		return noteId;
	}
}

