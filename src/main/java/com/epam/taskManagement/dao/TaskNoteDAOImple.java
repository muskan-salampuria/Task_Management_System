package com.epam.taskManagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.epam.taskManagement.models.Note;
import com.epam.taskManagement.models.Task;
import com.epam.taskManagement.utils.EntityManagerConnection;

@Repository
public class TaskNoteDAOImple implements TaskNoteDAO{

	private EntityManager eManager=null;

    public EntityManager getEntityManager() {
        if (eManager == null) {
            this.eManager = EntityManagerConnection.getEntityManagerConnection();
        }
        return this.eManager;
    }
	
	
     @Override
	public int createNewTask(Task task) {
    	 getEntityManager().getTransaction().begin();
        getEntityManager().persist(task);
        getEntityManager().getTransaction().commit();
		return(task.getId());
	}

     @Override
	public List<Task> listsAllTasks() {
		List<Task> taskList=getEntityManager().createQuery("Select t from Task t",Task.class).getResultList();
		return(taskList);
	}

     @Override
	public Task isTaskPresent(int id) {
		return(getEntityManager().find(Task.class,id));
	}

     @Override
	public int modifyTask(Task task) {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(task);
			getEntityManager().getTransaction().commit();
			return(task.getId());
		}

     @Override
	public boolean deleteTask(int id) {
		Task rdtask=isTaskPresent(id);
		if(rdtask==null)
			return false;
		else {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(rdtask);
			getEntityManager().getTransaction().commit();
			return true;
		}
	}
	
     @Override
	public Note isNotePresent(int noteID) {
		return(getEntityManager().find(Note.class,noteID));
	}

     @Override
	public boolean addNotes(int id, String noteInput) {
        
		Task rdtask=getEntityManager().find(Task.class,id);
		if(rdtask==null)
			return false;
		else {
			Note note=new Note();
	        note.setNotes(noteInput);
	        note.setTask(rdtask);
	        List<Note> notes=new ArrayList<>();
	        notes.add(note);
	        rdtask.setNoteList(notes);
	        
	        getEntityManager().getTransaction().begin();
	        getEntityManager().persist(rdtask);
	        getEntityManager().getTransaction().commit();
	        return true;
		}
	}
	
     @Override
	public List<Note> listAllNotes(){
		List<Note> noteList=getEntityManager().createQuery("Select n from Note n",Note.class).getResultList();
		return(noteList);
	}
	
     @Override
	public boolean modifyNote(int noteID, String noteInput) {
		Note note=isNotePresent(noteID);
		if(note!=null) {
			Task task=note.getTask();
			note.setNotes(noteInput);
			List<Note> notes=new ArrayList<>();
	        notes.add(note);
	        task.setNoteList(notes);
	        
	        getEntityManager().getTransaction().begin();
	        getEntityManager().persist(task);
	        getEntityManager().getTransaction().commit();
			return true;
		}
		else
			return false;
	}
	
     @Override
	public boolean deleteNote(int id) {
		Note note=isNotePresent(id);
		if(note==null)
			return false;
		else {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(note);
			getEntityManager().getTransaction().commit();
			return true;
		}
	}
}