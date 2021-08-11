package com.epam.taskManagement.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmptyListException extends Exception {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(StatusFormatException.class);
	
	public EmptyListException(String exception) {
		LOGGER.info(exception);
	}
}
