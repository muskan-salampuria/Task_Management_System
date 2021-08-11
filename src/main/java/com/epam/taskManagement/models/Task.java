package com.epam.taskManagement.models;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Tasks")
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;
	private String taskTitle;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status;
	
	@OneToMany(mappedBy="task", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Note> noteList;

	public void setNoteList(List<Note> notes) {
		this.noteList = notes;
	}

	public List<Note> getNoteList() {
		return noteList;
	}

	public void delNoteList(int index) {
		noteList.remove(index);
	}

	public void modifyNoteList(int index, Note note) {
		noteList.set(index, note);
	}

	public int getId() {
		return this.taskId;
	}

	public void setId(int id) {
		this.taskId = id;
	}

	public String getTitle() {
		return this.taskTitle;
	}

	public void setTitle(String title) {
		this.taskTitle = title;
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalDateTime start) {
		this.startTime = start;
	}

	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	public void setEndTime(LocalDateTime end) {
		this.endTime = end;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
