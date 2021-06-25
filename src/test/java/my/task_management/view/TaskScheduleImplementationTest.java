package my.task_management.view;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class TaskScheduleImplementationTest {

	TaskScheduleImplementation tSImple=new TaskScheduleImplementation();
	
	@Test
	public void testTitle() {
		assertEquals("Muskan",tSImple.titleInput());
	}

	@Test
	public void testStartDate() {
		assertEquals("28/06/2021 10:00:00",tSImple.startTimeInput());
	}
	
	@Test
	public void testModifyStartDate() {
		assertEquals("28/06/2021 09:00:00",tSImple.startTimeInput(LocalDateTime.parse("28/06/2021 10:00:00")));
	}
	
	@Test
	public void testEndDate() {
		assertEquals("28/06/2021 11:00:00",tSImple.endTimeInput(LocalDateTime.parse("28/06/2021 10:00:00")));
	}
	
	@Test
	public void testStatusInput() {
		assertEquals("PENDING",tSImple.statusInput());
	}
	
	@Test
	public void testNoteInput() {
		assertEquals("Note 1",tSImple.noteInput());
	}
	
	@Test
	public void testMultipleNoteInput() {
		assertEquals("[Note 1,Note 2]",tSImple.noteInput());
	}
}

