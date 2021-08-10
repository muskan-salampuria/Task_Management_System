package my.task_management.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTimeConflictException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(DateTimeConflictException.class);
	
	public DateTimeConflictException(String exception) {
		LOGGER.info(exception);
	}
}