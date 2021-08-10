package my.task_management.controller;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;

import my.task_management.dao.TaskScheduleDAO;
import my.task_management.exception.DateTimeConflictException;
import my.task_management.exception.EmptyListException;
import my.task_management.exception.TaskIDNotFoundException;
import my.task_management.models.Task;
import my.task_management.utils.Constants;
import my.task_management.utils.Validations;

@ExtendWith(MockitoExtension.class)
class TaskScheduleMockitoTest {
	
	@InjectMocks
	private TaskSchedule taskSchedule;
	
	@Mock
	TaskScheduleDAO taskScheduleDAOMocked;
	
	@Mock
	Validations validationsMocked;
	
//	@Mock
//	DateTimeFormatter dateFormat;
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//	private List<Note> list=new ArrayList<>();
	private List<Task> taskList=new ArrayList<>();
//	Note note=new Note();
	
	@BeforeEach
	public void setup(){
		Task task1=new Task();
		task1.setId(1);
		task1.setTitle("Reading");
		task1.setStartTime(LocalDateTime.parse("2021-08-28T11:00:00"));
		task1.setEndTime(LocalDateTime.parse("2021-08-28T11:30:00"));
		task1.setStatus("PENDING");
		taskList.add(task1);
//		Note note1=new Note();
//		note1.setId(1);
//		note1.setNotes("Muskan");
//		note1.setTask(task1);
//		list.add(note1);
		Task task2=new Task();
		task2.setId(2);
		task2.setTitle("Swimming");
		task2.setStartTime(LocalDateTime.parse("2021-08-28T17:00:00"));
		task2.setEndTime(LocalDateTime.parse("2021-08-28T17:30:00"));
		task2.setStatus("PENDING");
		taskList.add(task2);
//		Note note2=new Note();
//		note2.setId(2);
//		note2.setNotes("Salampuria");
//		note2.setTask(null);
//		list.add(note2);
	}
	
	@Test
	void addDataTest() {
	try {
		when(taskScheduleDAOMocked.createNewTask(Mockito.any(Task.class))).thenReturn("Task Added!!");
		when(validationsMocked.timeConflict(Mockito.any(LocalDateTime.class))).thenReturn(true);
	
		Assertions.assertEquals("Task Added!!", taskSchedule.createNewTask("Third_Task", LocalDateTime.parse("28/08/2021 14:00:00",dateFormat),LocalDateTime.parse("28/08/2021 13:00:00",dateFormat)));
	} catch (DateTimeConflictException e) {
		}
	}
	@Test
	void addDataTestException() {
	try {
//		doThrow(DateTimeConflictException.class).when(validationsMocked).timeConflict(Mockito.any(LocalDateTime.class));
		taskSchedule.createNewTask("Third_Task", LocalDateTime.parse("28/08/2021 11:00:00",dateFormat),LocalDateTime.parse("28/08/2021 11:30:00",dateFormat));
	} catch (DateTimeConflictException e) {
	Assertions.assertEquals(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME,e.getMessage());
	}
}
	
	@Test
	void readTaskDataTest() {
	try {
		when(taskScheduleDAOMocked.isTaskPresent(Mockito.anyInt())).thenReturn(Mockito.any(Task.class));
		Assertions.assertNotNull(taskSchedule.readTask(2));
	} 
	catch (TaskIDNotFoundException e) {}
	}
	
	@Test
	void readTaskDataTestException() {
	try {
		when(taskScheduleDAOMocked.isTaskPresent(Mockito.anyInt())).thenReturn(null);
//		doThrow(DateTimeConflictException.class).when(validationsMocked).timeConflict(Mockito.any(LocalDateTime.class));
		taskSchedule.readTask(12);
	} catch (TaskIDNotFoundException e) {
	Assertions.assertEquals(null,e.getMessage());
	}
	
}
	
	@Test
	void printTaskDataTest() {
	try {
//		when(taskScheduleDAOMocked.listsAllTasks()).thenReturn(Mockito.anyList());
//		System.out.println("Print");
		Assertions.assertNotNull(taskSchedule.listsAllTasks());
	} 
	catch (EmptyListException e) {}
	}
	
	@Test
	void printTaskDataTestException() {
	try {
		when(taskScheduleDAOMocked.isTaskPresent(Mockito.anyInt())).thenReturn(null);
//		doThrow(DateTimeConflictException.class).when(validationsMocked).timeConflict(Mockito.any(LocalDateTime.class));
		taskSchedule.listsAllTasks();
	} 
	catch (EmptyListException e) {
		Assertions.assertEquals(null,e.getMessage());
		}
	}
	
	@Test
	void modifyTaskTitleDataTest() {
		Task task=new Task();
		task.setId(4);
		task.setTitle("Gymming");
		task.setStartTime(LocalDateTime.parse("2021-08-28T18:00:00"));
		task.setEndTime(LocalDateTime.parse("2021-08-28T18:30:00"));
		task.setStatus("PENDING");
		taskList.add(task);
		when(taskScheduleDAOMocked.modifyTask(task)).thenReturn(Mockito.anyInt());
		Assertions.assertEquals("Title modified for Task :"+task.getId(),taskSchedule.modifyTaskTitle(task,"Happy"));
	}
	
	@Test
	void modifyTaskStatusDataTest() {
		Task task=new Task();
		task.setId(4);
		task.setTitle("Gymming");
		task.setStartTime(LocalDateTime.parse("2021-08-28T18:00:00"));
		task.setEndTime(LocalDateTime.parse("2021-08-28T18:30:00"));
		task.setStatus("PENDING");
		taskList.add(task);
		when(taskScheduleDAOMocked.modifyTask(task)).thenReturn(Mockito.anyInt());
		Assertions.assertEquals("Status modified for Task :"+taskScheduleDAOMocked.modifyTask(task),taskSchedule.modifyTaskStatus(task,"Completed"));
	}
	
	@Test
	void modifyTaskStartDateDataTest() {
		Task task=new Task();
		task.setId(4);
		task.setTitle("Gymming");
		task.setStartTime(LocalDateTime.parse("27/08/2021 18:00:00",dateFormat));
		task.setEndTime(LocalDateTime.parse("27/08/2021 18:30:00",dateFormat));
		task.setStatus("PENDING");
		taskList.add(task);
		try {
			when(taskScheduleDAOMocked.modifyTask(task)).thenReturn(Mockito.anyInt());
			when(validationsMocked.timeConflict(Mockito.any(LocalDateTime.class))).thenReturn(true);
			Assertions.assertEquals("StartTime modified for Task :0",taskSchedule.modifyTaskStartTime(task,LocalDateTime.parse("27/08/2021 13:00:00",dateFormat)));
		}
		 catch (DateTimeConflictException e) {}
	}
	
//	@Test
//	void modifyTaskStartDateDataTestException() {
//		Task task=new Task();
//		task.setId(4);
//		task.setTitle("Gymming");
//		task.setStartTime(LocalDateTime.parse("2021-08-28T18:00:00"));
//		task.setEndTime(LocalDateTime.parse("2021-08-28T18:30:00"));
//		task.setStatus("PENDING");
//		taskList.add(task);
//		try {
//			when(taskScheduleDAOMocked.modifyTask(task)).thenReturn(Mockito.anyInt());
//			when(validationsMocked.timeConflict(Mockito.any(LocalDateTime.class))).thenReturn(true);
//			taskSchedule.modifyTaskStartTime(task,LocalDateTime.parse("28/08/2021 13:00:00",dateFormat));
//		}
//		 catch (DateTimeConflictException e) {
//			 Assertions.assertEquals(Constants.YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME,e.getMessage());
//		 }
//	}

	@Test
	void deleteTaskTest() {
		try {
			when(taskScheduleDAOMocked.deleteTask(Mockito.anyInt())).thenReturn(true);
			Assertions.assertEquals("Task 2 Deleted",taskSchedule.deleteTasks(2));
		} 
		catch (TaskIDNotFoundException e) {}
	}
	
	
	@Test
	void deleteTaskTestException() {
		try {
			when(taskScheduleDAOMocked.deleteTask(Mockito.anyInt())).thenReturn(false);
			taskSchedule.deleteTasks(12);
		} 
		catch (TaskIDNotFoundException e) {
			Assertions.assertEquals(null,e.getMessage());
		}
	}
}
