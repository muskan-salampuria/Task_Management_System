package my.task_management.models;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
	private int taskId;
	private String taskTitle;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status;
	Notes notes = new Notes();

	public List<String> getNotesList() {
		return notes.getNoteList();
	}

	public void addNote(String note) {
		notes.addNoteList(note);
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
