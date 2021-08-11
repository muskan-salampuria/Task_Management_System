package com.epam.taskManagement.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.taskManagement.exception.DateTimeFormatException;
import com.epam.taskManagement.exception.DateTimeInputException;
import com.epam.taskManagement.exception.StatusFormatException;
@Component
public class Validations {

	private static final Logger LOGGER = LogManager.getLogger(Validations.class);

	public int choiceNumberValid(String ch) throws NumberFormatException {
		try {
			return Integer.parseInt(ch);
		} 
		catch (NumberFormatException | NullPointerException nfException) {
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
			if (status.equalsIgnoreCase(Constants.PENDING) || status.equalsIgnoreCase(Constants.COMPLETED))
				return status;
			else {
				throw new StatusFormatException(Constants.PLEASE_ENTER_ONLY_PENDING_COMPLETED);
			}
	}

	public LocalDateTime startDateValid(String date) throws DateTimeFormatException,DateTimeInputException{
		try {
			LocalDateTime startDate = dateValid(date);
			if (startDate.isAfter(LocalDateTime.now())) {
					return startDate;
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
	public LocalDateTime startDateValid(String startDate, LocalDateTime endDate) throws DateTimeFormatException,DateTimeInputException {
		try {
			LocalDateTime startDateTime = dateValid(startDate);
			if (endDate.isAfter(startDateTime))
					return startDateTime;
			else 
				throw new DateTimeInputException(Constants.PLEASE_ENTER_DATE_TIME_BEFORE_END_DATE_TIME);
		} 
		catch (Exception e) {
			throw new DateTimeFormatException("");
		}
	}

	public LocalDateTime endDateValid(LocalDateTime startDate, String endDate) throws DateTimeFormatException,DateTimeInputException {
		try {
			LocalDateTime endDateTime = dateValid(endDate);
			if (endDateTime.isAfter(startDate)) 
					return endDateTime;
			else 
				throw new DateTimeInputException(Constants.PLEASE_ENTER_DATE_TIME_AFTER_START_DATE_TIME);
		} 
		catch (Exception e) {
			throw new DateTimeFormatException("");

		}
	}
}
