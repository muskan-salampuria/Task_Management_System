package com.epam.taskManagement.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.taskManagement.utils.Constants;

public class DateTimeConflictException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(DateTimeConflictException.class);
	
	public DateTimeConflictException( ) {
		LOGGER.info(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME);
	}
}