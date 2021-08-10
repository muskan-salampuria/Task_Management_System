package my.task_management.dao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import my.task_management.models.Note;
import my.task_management.models.Task;
import my.task_management.view.TaskScheduleInputsValid;


public class TaskScheduleDAO {

	TaskScheduleInputsValid taskImplementationValidator = new TaskScheduleInputsValid();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	 EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("my-local-mysql");
     EntityManager eManager = emFactory.createEntityManager();

	public String createNewTask(Task task) {
		eManager.getTransaction().begin();
        eManager.persist(task);
        eManager.getTransaction().commit();
		return("Task Added!!");
	}

	public List<Task> listsAllTasks() {
		List<Task> taskList=eManager.createQuery("Select t from Task t",Task.class).getResultList();
		return(taskList);
	}

	public Task isTaskPresent(int id) {
		return(eManager.find(Task.class,id));
	}

	public int modifyTask(Task task) {
			eManager.getTransaction().begin();
			eManager.persist(task);
			eManager.getTransaction().commit();
			return(task.getId());
		}

	public boolean deleteTask(int id) {
		Task rdtask=isTaskPresent(id);
		if(rdtask==null)
			return false;
		else {
			eManager.getTransaction().begin();
			eManager.remove(rdtask);
			eManager.getTransaction().commit();
			return true;
		}
	}
	
	public Note isNotePresent(int noteID) {
		return(eManager.find(Note.class,noteID));
	}

	public boolean addNotes(int id, String noteInput) {
        
		Task rdtask=eManager.find(Task.class,id);
		if(rdtask==null)
			return false;
		else {
			Note note=new Note();
	        note.setNotes(noteInput);
	        note.setTask(rdtask);
	        List<Note> notes=new ArrayList<>();
	        notes.add(note);
	        rdtask.setNoteList(notes);
	        
	        eManager.getTransaction().begin();
	        eManager.persist(rdtask);
	        eManager.getTransaction().commit();
	        return true;
		}
	}
	
	public List<Note> listAllNotes(){
		List<Note> noteList=eManager.createQuery("Select n from Note n",Note.class).getResultList();
		return(noteList);
	}
	
	public boolean modifyNote(int noteID, String noteInput) {
		Note note=isNotePresent(noteID);
		if(note!=null) {
			Task task=note.getTask();
			note.setNotes(noteInput);
			List<Note> notes=new ArrayList<>();
	        notes.add(note);
	        task.setNoteList(notes);
	        
	        eManager.getTransaction().begin();
	        eManager.persist(task);
	        eManager.getTransaction().commit();
			return true;
		}
		else
			return false;
	}
	
	public boolean deleteNote(int id) {
		Note note=isNotePresent(id);
		if(note==null)
			return false;
		else {
			eManager.getTransaction().begin();
			eManager.remove(note);
			eManager.getTransaction().commit();
			return true;
		}
	}
}