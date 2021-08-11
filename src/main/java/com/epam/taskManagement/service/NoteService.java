package com.epam.taskManagement.service;

import java.util.List;

import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.NoteIDNotFoundException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Note;

public interface NoteService {

	public abstract String addNotes(int taskID, String note) throws TaskIDNotFoundException ;
	public abstract List<Note> printNotes() throws EmptyListException ;
	public abstract String modifyNote(int noteID, String noteInput) throws NoteIDNotFoundException ;
	public abstract String deleteNote(int noteID) throws NoteIDNotFoundException ;
	
}
