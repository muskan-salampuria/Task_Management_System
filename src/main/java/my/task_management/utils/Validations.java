package my.task_management.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.dao.TaskCollections;
import my.task_management.exception.DateTimeConflictException;
import my.task_management.exception.DateTimeFormatException;
import my.task_management.exception.DateTimeInputException;
import my.task_management.exception.StatusFormatException;

public class Validations {

	private static final Logger LOGGER = LogManager.getLogger(Validations.class);

	public int choiceValid(String ch) throws NumberFormatException {
		try {
			return Integer.parseInt(ch);
		} 
		catch (NumberFormatException nfException) {
			LOGGER.error(Constants.PLEASE_ENTER_A_VALID_NUMBER);
			throw new NumberFormatException();
		}
	}

	public LocalDateTime dateValid(String dateTime)throws DateTimeFormatException {
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			return (LocalDateTime.parse(dateTime, dateFormat));
		}
		catch (Exception e) {
			LOGGER.warn(Constants.PLEASE_ENTER_A_VALID_DATE);
			throw new DateTimeFormatException();
		}
	}

	public String statusValid(String status) throws StatusFormatException{
		try {
			if (status.equalsIgnoreCase(Constants.PENDING) || status.equalsIgnoreCase(Constants.COMPLETED))
				return status;
			else {
				LOGGER.warn(Constants.PLEASE_ENTER_ONLY_PENDING_COMPLETED);
				throw new StatusFormatException();
			}
		} 
		catch (Exception e) {
			throw new StatusFormatException();
		}
	}

	public LocalDateTime startDateValid(String date) throws DateTimeFormatException,DateTimeInputException,DateTimeConflictException{
		try {
			LocalDateTime startDate = dateValid(date);
			if (startDate.isAfter(LocalDateTime.now())) {
				if (timeConflict(startDate)) {
					LOGGER.warn(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
					throw new DateTimeConflictException();
				} 
				else {
					return startDate;
				}
			}
			else {
				LOGGER.warn(Constants.PLEASE_ENTER_FUTURE_DATE_TIME);
				throw new DateTimeInputException();
			}
		} 
		catch (Exception e) {
			throw new DateTimeFormatException();
		}
	}

	public LocalDateTime startDateValid(String startDate, LocalDateTime endDate) throws DateTimeFormatException, DateTimeConflictException,DateTimeInputException {
		try {
			LocalDateTime startDateTime = dateValid(startDate);
			if (endDate.isAfter(startDateTime)) {
				if (!timeConflict(startDateTime)) {
					return startDateTime;
				}
				else {
					LOGGER.warn(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
					throw new DateTimeConflictException();
				}
			}
			else {
				LOGGER.warn(Constants.PLEASE_ENTER_DATE_TIME_BEFORE_END_DATE_TIME);
				throw new DateTimeInputException();
			}
		} 
		catch (Exception e) {
			throw new DateTimeFormatException();
		}
	}

	public LocalDateTime endDateValid(LocalDateTime startDate, String endDate) throws DateTimeFormatException, DateTimeConflictException,DateTimeInputException {
		try {
			LocalDateTime endDateTime = dateValid(endDate);
			if (endDateTime.isAfter(startDate)) {
				if (timeConflict(endDateTime)) {
					LOGGER.warn(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
					throw new DateTimeConflictException();
				} 
				else {
					return endDateTime;
				}
			}
			else {
				LOGGER.warn(Constants.PLEASE_ENTER_DATE_TIME_AFTER_START_DATE_TIME);
				throw new DateTimeInputException();
			}
		} 
		catch (Exception e) {
			throw new DateTimeFormatException();

		}
	}

	public boolean timeConflict(LocalDateTime dateTime) {
		return new TaskCollections().getTaskList().stream()
				.anyMatch(task -> (task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
						|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime)));
	}
}
