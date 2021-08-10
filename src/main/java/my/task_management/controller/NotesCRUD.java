package my.task_management.controller;

import java.util.List;
import my.task_management.dao.TaskScheduleDAO;
import my.task_management.exception.EmptyListException;
import my.task_management.exception.NoteIDNotFoundException;
import my.task_management.exception.TaskIDNotFoundException;
import my.task_management.models.Note;
import my.task_management.utils.Constants;

public class NotesCRUD {

	TaskScheduleDAO taskScheduleDAO = new TaskScheduleDAO();

	public String addNotes(int taskID, String note) throws TaskIDNotFoundException {
		if(taskScheduleDAO.addNotes(taskID,note))
			return(Constants.NOTE_ADDED);
		else
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
	}
	
	public List<Note> printNotes() throws EmptyListException {
		List<Note> notesList=taskScheduleDAO.listAllNotes();
		if (notesList.isEmpty()) {
			throw new EmptyListException("No Notes to Print");
		} else {
			return notesList;
		}
	}
	
	public String modifyNote(int noteID, String noteInput) throws NoteIDNotFoundException {
		if(taskScheduleDAO.modifyNote(noteID,noteInput)) {
			String result = "Note " + noteID + " Modified";
			return(result);
		}
		else
			throw new NoteIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
	}
	
	public String deleteNote(int noteID) throws NoteIDNotFoundException {
		if(taskScheduleDAO.deleteNote(noteID)) {
			String result = "Note " + noteID + " Deleted";
			return(result);
		}
		else
			throw new NoteIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
	}
	
}
