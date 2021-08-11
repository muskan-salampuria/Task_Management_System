package com.epam.taskManagement.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoteIDNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(NoteIDNotFoundException.class);
	
	public NoteIDNotFoundException(String exception) {
		LOGGER.error(exception);
	}
}
