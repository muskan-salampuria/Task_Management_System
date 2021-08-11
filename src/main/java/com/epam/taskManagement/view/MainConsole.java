package com.epam.taskManagement.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.taskManagement.utils.Constants;

@Component
public class MainConsole {
	private static final Logger LOGGER = LogManager.getLogger(MainConsole.class);
	private Map<Integer, Runnable> choices=new HashMap<Integer, Runnable>();
	@Autowired
	private TaskNotesOperations operations ;
	
	@Autowired
	private TaskScheduleInputsValid taskInput;
	
	{
		choices.put(1,()->operations.createNewTask());
		choices.put(2, ()->operations.listsAllTasks());
		choices.put(3, ()->operations.modifyTask());
		choices.put(4, ()->operations.deleteTasks());
		choices.put(5, ()->operations.readTask());
		choices.put(6, ()->operations.addNotes());
		choices.put(7, ()->operations.printNotes());
		choices.put(8, ()->operations.modifyNote());
		choices.put(9, ()->operations.deleteNote());
	}
	
	public void execute() {
		while (true) {
			try {
				LOGGER.info("Enter a choice :");
				int choice = taskInput.menuChoiceInput();
				if (choice<1 || choice>choices.size()) {
					LOGGER.info(Constants.PLEASE_ENTER_A_VALID_CHOICE);
				}
				else if(choice==10) {
					break;
				}
				else {
					choices.get(choice).run();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage() );
			}
		}
	}
}
