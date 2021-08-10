package my.task_management.controller;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
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
import my.task_management.exception.EmptyListException;
import my.task_management.exception.NoteIDNotFoundException;
import my.task_management.exception.TaskIDNotFoundException;
import my.task_management.models.Note;
import my.task_management.models.Task;
import my.task_management.utils.Constants;



@ExtendWith(MockitoExtension.class)
class NotesCRUDMockitoTest {

	@InjectMocks
	private NotesCRUD notesCRUDMocked;
	
	@Mock
	TaskScheduleDAO taskScheduleDAOMocked;
	
	private List<Note> list=new ArrayList<>();
	private List<Task> taskList=new ArrayList<>();
	Note note=new Note();
	
	@BeforeEach
	public void setup(){
		Task task1=new Task();
		task1.setId(1);
		task1.setTitle("Reading");
		task1.setStartTime(LocalDateTime.parse("2021-08-01T11:00:00"));
		task1.setEndTime(LocalDateTime.parse("2021-08-01T11:30:00"));
		task1.setStatus("PENDING");
		taskList.add(task1);
		Note note1=new Note();
		note1.setId(1);
		note1.setNotes("Muskan");
		note1.setTask(task1);
		list.add(note1);
		Task task2=new Task();
		task2.setId(2);
		task2.setTitle("Swimming");
		task2.setStartTime(LocalDateTime.parse("2021-08-01T17:00:00"));
		task2.setEndTime(LocalDateTime.parse("2021-08-01T17:30:00"));
		task2.setStatus("PENDING");
		taskList.add(task1);
		Note note2=new Note();
		note2.setId(2);
		note2.setNotes("Salampuria");
		note2.setTask(null);
		list.add(note2);
	}
	
	@Test
	void addNoteTest1()  {

		try {
			when(taskScheduleDAOMocked.addNotes(Mockito.anyInt(),Mockito.anyString())).thenReturn(true);
			Assertions.assertEquals(Constants.NOTE_ADDED, notesCRUDMocked.addNotes(2,"Swimming"));
		} catch (TaskIDNotFoundException e) {
			}
		}

	@Test
	void addNoteTest2() {
		try {
			when(taskScheduleDAOMocked.addNotes(Mockito.anyInt(),Mockito.anyString())).thenReturn(true);
			
//			doThrow(TaskIDNotFoundException.class).when(taskScheduleDAOMocked).addNotes(Mockito.anyInt(),Mockito.anyString());
			notesCRUDMocked.addNotes(12,"Swimming");			
			} catch (TaskIDNotFoundException e) {
			Assertions.assertEquals(Constants.TASK_ID_NOT_FOUND,e.getMessage());
			}
	}
	
	
	@Test
	void deleteNoteTest1() {

		try {
			when(taskScheduleDAOMocked.deleteNote(Mockito.anyInt())).thenReturn(true);
			Assertions.assertEquals("Note 2 Deleted", notesCRUDMocked.deleteNote(2));
		} catch (NoteIDNotFoundException e) {
			}
		}

	@Test
	void deleteNoteTest2() {
		try {
			when(taskScheduleDAOMocked.deleteNote(Mockito.anyInt())).thenReturn(true);
			
//			doThrow(TaskIDNotFoundException.class).when(taskScheduleDAOMocked).addNotes(Mockito.anyInt(),Mockito.anyString());
			notesCRUDMocked.deleteNote(12);			
			} catch (NoteIDNotFoundException e) {
			Assertions.assertEquals(Constants.NOTE_ID_NOT_FOUND,e.getMessage());
			}
	}
	
	@Test
	void modifyNoteTest1() {

		try {
			when(taskScheduleDAOMocked.modifyNote(Mockito.anyInt(),Mockito.anyString())).thenReturn(true);
			Assertions.assertEquals("Note 2 Modified", notesCRUDMocked.modifyNote(2,"Muskan"));
		} catch (NoteIDNotFoundException e) {
			}
		}

	@Test
	void modifyNoteTest2() {
		try {
			when(taskScheduleDAOMocked.modifyNote(Mockito.anyInt(),Mockito.anyString())).thenReturn(true);
			
//			doThrow(TaskIDNotFoundException.class).when(taskScheduleDAOMocked).addNotes(Mockito.anyInt(),Mockito.anyString());
			notesCRUDMocked.modifyNote(12,"Muskan");			
			} catch (NoteIDNotFoundException e) {
			Assertions.assertEquals(Constants.NOTE_ID_NOT_FOUND,e.getMessage());
			}
	}
	
	@Test
	void printNoteTest1() {

		try {
			when(taskScheduleDAOMocked.listAllNotes()).thenReturn(list);
			Assertions.assertEquals(list, notesCRUDMocked.printNotes());
		} catch (EmptyListException e) {
			} 
		}

	@Test
	void printNoteTest2() {
		try {
			when(taskScheduleDAOMocked.listAllNotes()).thenReturn(list);
			
//			doThrow(TaskIDNotFoundException.class).when(taskScheduleDAOMocked).addNotes(Mockito.anyInt(),Mockito.anyString());
			notesCRUDMocked.printNotes();			
			} catch (EmptyListException e) {
			Assertions.assertEquals(Constants.NOTE_ID_NOT_FOUND,e.getMessage());
			}
	}
}
