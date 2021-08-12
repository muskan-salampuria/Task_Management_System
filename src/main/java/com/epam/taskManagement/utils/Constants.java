package com.epam.taskManagement.utils;

public class Constants {
	public static final String PLEASE_ENTER_A_VALID_NUMBER = "Please Enter a Valid Number";
	public static final String PLEASE_ENTER_FUTURE_DATE_TIME = "Please Enter Future Date Time";
	public static final String PLEASE_ENTER_DATE_TIME_BEFORE_END_DATE_TIME = "!!! Please Enter Date Time Before End Date Time !!!";
	public static final String PLEASE_ENTER_DATE_TIME_AFTER_START_DATE_TIME = "!!! Please Enter Date Time After Start Date Time !!!";
	public static final String RE_ENTER_THE_DATA = "Re Enter the Data";
	public static final String PLEASE_ENTER_ONLY_PENDING_COMPLETED = "Please Enter only PENDING/COMPLETED";
	public static final String COMPLETED = "COMPLETED";
	public static final String PENDING = "PENDING";
	public static final String PLEASE_ENTER_A_VALID_DATE = "Please Enter a valid Date";
	public static final String YOU_HAVE_ANOTHER_ACTIVITY_ASSIGNED_IN_THAT_TIME = "!!! You have another activity assigned in that Time !!!";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String LINES = "-----------------------------------------------------------------------------------------------------------------------------";
	public static final String PLEASE_ENTER_A_VALID_CHOICE = "Please Enter a Valid Choice";
	public static final String TASK_ID_NOT_FOUND = "!!! Task ID not found !!!";
	public static final String LIST_IS_EMPTY = "!!! List is Empty !!!";
	public static final String NOTE_ID_NOT_FOUND = "!!! Note ID not found !!!";
	public static final String NO_TASKS = "                                             !!!!!! NO TASKS AVAILABLE !!!!!!!!!                                      ";
	public static final String ENTER_END_DATE_TIME = "Enter End DateTime (dd/MM/yyyy HH:mm:ss) : ";
	public static final String ENTER_START_DATE_TIME = "Enter Start Date Time (dd/MM/yyyy HH:mm:ss) : ";
	public static final String ENTER_THE_STATUS_COMPLETED_PENDING = "Enter the status (Completed/Pending) : ";
	public static final String CHOICE_MENU = "*****************************************************\nSelect Operation : "
			+ "\n1. Create New Task" + "\n2. List All Tasks" + "\n3. Modify Task by TaskID"
			+ "\n4. Delete Task by TaskID" + "\n5. Read Task by TaskID" + "\n6. Add Notes by TaskID"
			+ "\n7. Print Notes" + "\n8. Update Notes by NoteID" + "\n9. Delete Notes by NoteID"
			+ "\n10.EXIT \n*****************************************************";
	public static final String MODIFY_CHOICE_OPTIONS = "****************************\nChoose which one to modify : \n1.Title \n2.Start Time \n3.End Time \n4.Status \n5.BACK \n****************************";
	public static final String TASK_LIST = "------------------------------------------------------------ TASK LIST ------------------------------------------------------";
	public static final String ENTER_NOTE = "Enter Note:";
	public static final String NOTE_ADDED = "Note Added";
	public static final String RE_ENTER_YOUR_CHOICE = "Re Enter your choice";
	public static final String NOTES_TABLE_HEADING = String.format("%10s | %20s | %10s ", "Note ID", "Notes",
			"Task ID");
	public static final String NOTES_LIST = "------------------------------------------------------------ NOTES LIST ------------------------------------------------------";
	public static final String NO_NOTES = "                                             !!!!!! NO NOTES AVAILABLE !!!!!!!!!                                      ";
	public static final String TASK_TABLE_HEADING = String.format("%10s | %15s | %20s | %20s | %15s ", "Task ID",
			"Title", "Start", "End", "Status");
	public static final String PROGRAM_CLOSED = "!!!!!!!!!!!!!!!  Program Closed  !!!!!!!!!!!!!!!!";

	private Constants() {
		// private constructor to implicit public one
	}
}
