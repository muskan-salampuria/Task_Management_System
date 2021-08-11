package com.epam.taskManagement.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTimeFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(DateTimeFormatException.class);
	
	public DateTimeFormatException(String exception) {
		LOGGER.info(exception);
	}
}
