package my.task_management.models;

import java.util.ArrayList;
import java.util.List;

public class Notes {
	
	private List<String> noteList = new ArrayList<>();

	public List<String> getNoteList() {
		return this.noteList;
	}

	public void addNoteList(String note) {
		this.noteList.add(note);
	}
}
