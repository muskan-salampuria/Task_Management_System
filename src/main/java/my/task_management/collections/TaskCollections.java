package my.task_management.collections;

import java.util.ArrayList;
import java.util.List;

import my.task_management.models.Task;

public class TaskCollections {
	private static List<Task> taskList = new ArrayList<>();

	
	public void addTask(Task task) {
		taskList.add(task);
	}

	public void deleteTask(Task task) {
		taskList.remove(task);
	}

	public List<Task> getTaskList() {
		return taskList;
	}
}

