//package com.epam.taskManagement.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.epam.taskManagement.models.Note;
//import com.epam.taskManagement.models.Task;
//
//@Repository
//public class TaskNoteDAOImple implements TaskNoteDAO {
//
//	@Autowired
//	private EntityManager eManager;
//
////    public EntityManager getEntityManager() {
////        if (eManager == null) {
////            this.eManager = EntityManagerConnection.getEntityManagerConnection();
////        }
////        return this.eManager;
////    }
//
//	@Override
//	public int createNewTask(Task task) {
//		eManager.getTransaction().begin();
//		eManager.persist(task);
//		eManager.getTransaction().commit();
//		return (task.getId());
//	}
//
//	@Override
//	public List<Task> listsAllTasks() {
//		List<Task> taskList = eManager.createQuery("Select t from Task t", Task.class).getResultList();
//		return (taskList);
//	}
//
//	@Override
//	public Task isTaskPresent(int id) {
//		return (eManager.find(Task.class, id));
//	}
//
//	@Override
//	public int modifyTask(Task task) {
//		eManager.getTransaction().begin();
//		eManager.persist(task);
//		eManager.getTransaction().commit();
//		return (task.getId());
//	}
//
//	@Override
//	public boolean deleteTask(int id) {
//		Task rdtask = isTaskPresent(id);
//		if (rdtask == null)
//			return false;
//		else {
//			eManager.getTransaction().begin();
//			eManager.remove(rdtask);
//			eManager.getTransaction().commit();
//			return true;
//		}
//	}
//
//	@Override
//	public Note isNotePresent(int noteID) {
//		return (eManager.find(Note.class, noteID));
//	}
//
//	@Override
//	public boolean addNotes(int id, String noteInput) {
//
//		Task rdtask = eManager.find(Task.class, id);
//		if (rdtask == null)
//			return false;
//		else {
//			Note note = new Note();
//			note.setNotes(noteInput);
//			note.setTask(rdtask);
//			List<Note> notes = new ArrayList<>();
//			notes.add(note);
//			rdtask.setNoteList(notes);
//
//			eManager.getTransaction().begin();
//			eManager.persist(rdtask);
//			eManager.getTransaction().commit();
//			return true;
//		}
//	}
//
//	@Override
//	public List<Note> listAllNotes() {
//		List<Note> noteList = eManager.createQuery("Select n from Note n", Note.class).getResultList();
//		return (noteList);
//	}
//
//	@Override
//	public boolean modifyNote(int noteID, String noteInput) {
//		Note note = isNotePresent(noteID);
//		if (note != null) {
//			Task task = note.getTask();
//			note.setNotes(noteInput);
//			List<Note> notes = new ArrayList<>();
//			notes.add(note);
//			task.setNoteList(notes);
//
//			eManager.getTransaction().begin();
//			eManager.persist(task);
//			eManager.getTransaction().commit();
//			return true;
//		} else
//			return false;
//	}
//
//	@Override
//	public boolean deleteNote(int id) {
//		Note note = isNotePresent(id);
//		if (note == null)
//			return false;
//		else {
//			eManager.getTransaction().begin();
//			eManager.remove(note);
//			eManager.getTransaction().commit();
//			return true;
//		}
//	}
//}