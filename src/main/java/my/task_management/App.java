package my.task_management;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.controller.TaskSchedule;
import my.task_management.utils.Constants;
import my.task_management.view.TaskScheduleImplementation;

/* Java Task Manager*/

public class App {
	
	private static final Logger LOGGER= LogManager.getLogger(App.class);
	public static final TaskSchedule taskSchedule=new TaskSchedule();
	private static Map<Integer, Runnable> choices=new HashMap<>();
	static {
		choices.put(1, ()->taskSchedule.createNewTask());
		choices.put(2, ()->taskSchedule.listsAllTasks());
		choices.put(3, ()->taskSchedule.modifyTask());
		choices.put(4, ()->taskSchedule.deleteTasks());
		choices.put(5, ()->taskSchedule.addNotes());
		choices.put(6, ()->taskSchedule.readTask());
		choices.put(7, ()->System.exit(0));
	}
	
	public static void main(final String[] args) {
		while (true) {
			final int choice = new TaskScheduleImplementation().menuChoiceInput();
			if (choice<1 || choice>choices.size()) {
				LOGGER.info(Constants.PLEASE_ENTER_A_VALID_CHOICE);
			}
			else if(choice==7) {
				break;
			}
			else {
				choices.get(choice).run();
			}
		}
	}
}
