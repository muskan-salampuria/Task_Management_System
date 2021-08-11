package com.epam.taskManagement.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Notes")
public class Note {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 int id;
 String notes;
 
 @ManyToOne(targetEntity= Task.class)
 Task task;
public Task getTask() {
	return task;
}
public void setTask(Task task) {
	this.task = task;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}
}
