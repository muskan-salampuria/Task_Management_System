package com.epam.taskManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.taskManagement.exception.EmptyListException;
import com.epam.taskManagement.exception.NoteIDNotFoundException;
import com.epam.taskManagement.exception.TaskIDNotFoundException;
import com.epam.taskManagement.models.Note;
import com.epam.taskManagement.models.Task;
import com.epam.taskManagement.repository.NoteRepository;
import com.epam.taskManagement.repository.TaskRepository;
import com.epam.taskManagement.utils.Constants;

@Service
public class NoteServiceImple implements NoteService {

	@Autowired
	NoteRepository noteRepo;

	@Autowired
	TaskRepository taskRepo;

	@Override
	public Note addNotes(int taskID, String noteInput) throws TaskIDNotFoundException {
		Optional<Task> task = taskRepo.findById(taskID);
		if (!task.isPresent())
			throw new TaskIDNotFoundException(Constants.TASK_ID_NOT_FOUND);
		Note note = new Note();
		Task addedtask = taskRepo.findById(taskID).get();
		note.setNotes(noteInput);
		note.setTask(addedtask);
		List<Note> notes = new ArrayList<>();
		notes.add(note);
		addedtask.setNoteList(notes);
		taskRepo.save(addedtask);
		return note;
	}

	@Override
	public List<Note> printNotes() throws EmptyListException {
		List<Note> notesList = noteRepo.findAll();
		if (notesList.isEmpty()) {
			throw new EmptyListException("No Notes to Print");
		} else {
			return notesList;
		}
	}

	@Override
	public int modifyNote(int noteID, String noteInput) throws NoteIDNotFoundException {
		Note note = noteRepo.findById(noteID).get();
		if (Objects.isNull(note))
			throw new NoteIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
		Task task = note.getTask();
		note.setNotes(noteInput);
		List<Note> notes = new ArrayList<>();
		notes.add(note);
		task.setNoteList(notes);
		taskRepo.save(task);
		return (noteID);
	}

	@Override
	public int deleteNote(int noteID) throws NoteIDNotFoundException {
		System.out.println("delete calling");
		Note note = noteRepo.findById(noteID).get();
		if (Objects.isNull(note))
			throw new NoteIDNotFoundException(Constants.NOTE_ID_NOT_FOUND);
		System.out.println("note id found");
		System.out.println("getting znote list");
		noteRepo.deleteById(noteID);
		return noteID;
	}
}
