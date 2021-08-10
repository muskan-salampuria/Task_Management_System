package my.task_management;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.exception.DateTimeConflictException;
import my.task_management.utils.Constants;
import my.task_management.view.TaskNotesOperations;
import my.task_management.view.TaskScheduleInputsValid;

/* Java Task Manager*/

public class App {
	
	private static final Logger LOGGER= LogManager.getLogger(App.class);
	public static final TaskNotesOperations operations=new TaskNotesOperations();
	private static Map<Integer, Runnable> choices=new HashMap<>();
	static {
		choices.put(1, () -> {
			try {
				operations.createNewTask();
			} catch (DateTimeConflictException e) {
			}
		});
		choices.put(2, operations::listsAllTasks);
		choices.put(3, operations::modifyTask);
		choices.put(4, operations::deleteTasks);
		choices.put(5, operations::readTask);
		choices.put(6, operations::addNotes);
		choices.put(7, operations::printNotes);
		choices.put(8, operations::modifyNote);
		choices.put(9, operations::deleteNote);
		choices.put(10, ()->System.exit(0));
	}
	
	public static void main(final String[] args) {
		while (true) {
			final int choice = new TaskScheduleInputsValid().menuChoiceInput();
			if (choice<1 || choice>choices.size()) {
				LOGGER.info(Constants.PLEASE_ENTER_A_VALID_CHOICE);
			}
			else if(choice==10) {
				break;
			}
			else {
				choices.get(choice).run();
			}
		}
	}
}
