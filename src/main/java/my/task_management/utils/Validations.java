package my.task_management.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.dao.TaskScheduleDAO;
import my.task_management.exception.DateTimeConflictException;
import my.task_management.exception.DateTimeFormatException;
import my.task_management.exception.DateTimeInputException;
import my.task_management.exception.StatusFormatException;
import my.task_management.models.Task;

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
			throw new DateTimeFormatException(Constants.PLEASE_ENTER_A_VALID_DATE);
		}
	}

	public String statusValid(String status) throws StatusFormatException{
		try {
			if (status.equalsIgnoreCase(Constants.PENDING) || status.equalsIgnoreCase(Constants.COMPLETED))
				return status;
			else {
				throw new StatusFormatException(Constants.PLEASE_ENTER_ONLY_PENDING_COMPLETED);
			}
		} 
		catch (Exception e) {
			throw new StatusFormatException("");
		}
	}

	public LocalDateTime startDateValid(String date) throws DateTimeFormatException,DateTimeInputException,DateTimeConflictException{
		try {
			LocalDateTime startDate = dateValid(date);
			if (startDate.isAfter(LocalDateTime.now())) {
//				if (timeConflict(0,startDate)) {
//					throw new DateTimeConflictException(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
//				} 
//				else {
					return startDate;
//				}
			}
			else {
				throw new DateTimeInputException(Constants.PLEASE_ENTER_FUTURE_DATE_TIME);
			}
		} 
		catch (Exception e) {
			throw new DateTimeFormatException("");
		}
	}

	//Overload
	public LocalDateTime startDateValid(String startDate, LocalDateTime endDate) throws DateTimeFormatException, DateTimeConflictException,DateTimeInputException {
		try {
			LocalDateTime startDateTime = dateValid(startDate);
			if (endDate.isAfter(startDateTime)) {
//				if (!timeConflict(taskID,startDateTime)) {
					return startDateTime;
//				}
//				else {
//					throw new DateTimeConflictException(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
//				}
			}
			else {
				throw new DateTimeInputException(Constants.PLEASE_ENTER_DATE_TIME_BEFORE_END_DATE_TIME);
			}
		} 
		catch (Exception e) {
			throw new DateTimeFormatException("");
		}
	}

	public LocalDateTime endDateValid(LocalDateTime startDate, String endDate) throws DateTimeFormatException, DateTimeConflictException,DateTimeInputException {
		try {
			LocalDateTime endDateTime = dateValid(endDate);
			if (endDateTime.isAfter(startDate)) {
//				if (timeConflict(0,endDateTime)) {
//					throw new DateTimeConflictException(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
//				} 
//				else {
					return endDateTime;
//				}
			}
			else {
				throw new DateTimeInputException(Constants.PLEASE_ENTER_DATE_TIME_AFTER_START_DATE_TIME);
			}
		} 
		catch (Exception e) {
			throw new DateTimeFormatException("");

		}
	}

	public boolean timeConflict(LocalDateTime dateTime) {
		List<Task> taskList=new TaskScheduleDAO().listsAllTasks();
		return taskList.stream()
				.anyMatch(task -> (task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
						|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime)));
	}
	
	//Overload
	public boolean timeConflict(int taskID, LocalDateTime dateTime) {
		List<Task> taskList=new TaskScheduleDAO().listsAllTasks();
		return taskList.stream()
				.anyMatch(task -> (task.getId()!=taskID) && ((task.getStartTime().isBefore(dateTime) && task.getEndTime().isAfter(dateTime))
						|| (task.getStartTime().isEqual(dateTime) || task.getEndTime().isEqual(dateTime))));
	}
}
