package my.task_management.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.task_management.collections.TaskCollections;
import my.task_management.controller.NotesCRUD;
import my.task_management.controller.TaskSchedule;
import my.task_management.dao.TaskScheduleDAO;
import my.task_management.exception.DateTimeConflictException;
import my.task_management.exception.EmptyListException;
import my.task_management.exception.NoteIDNotFoundException;
import my.task_management.exception.TaskIDNotFoundException;
import my.task_management.models.Note;
import my.task_management.models.Task;
import my.task_management.utils.Constants;
import my.task_management.utils.Validations;

public class TaskNotesOperations {

		private static final Logger LOGGER = LogManager.getLogger(TaskNotesOperations.class);

		TaskSchedule taskSchedule = new TaskSchedule();
		TaskScheduleInputsValid taskValidInputs = new TaskScheduleInputsValid();
		TaskScheduleDAO taskScheduleDAO = new TaskScheduleDAO();
		TaskCollections taskCollections = new TaskCollections();
		NotesCRUD noteCRUD = new NotesCRUD();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		Validations validate = new Validations();
		

		public void createNewTask() throws DateTimeConflictException {

			String title=taskValidInputs.titleInput();
			LocalDateTime startTime;
			LocalDateTime endTime;
			boolean flag=true;
			while(flag){
				startTime = taskValidInputs.startTimeInput();
				endTime = taskValidInputs.endTimeInput(startTime);
				try {
					String result=taskSchedule.createNewTask(title,startTime,endTime);
					LOGGER.info(result);
					flag=false;
					}
				catch(DateTimeConflictException e) {
					LOGGER.warn(Constants.RE_ENTER_THE_DATA);
				}
			}
		}

		public void listsAllTasks() {
			LOGGER.info(Constants.TASK_LIST);
			LOGGER.info(Constants.TASK_TABLE_HEADING);
			try {
				List<Task> allTask=taskSchedule.listsAllTasks();
				for (Task task : allTask) {
					LOGGER.info(Constants.LINES);
					String taskDetails = String.format("%10s | %15s | %20s | %20s | %15s ", task.getId(), task.getTitle(),
							dateFormat.format(task.getStartTime()), dateFormat.format(task.getEndTime()), task.getStatus());
					LOGGER.info(taskDetails);
				}
				LOGGER.info(Constants.LINES);
			}
			catch(EmptyListException elException) {
				LOGGER.info(Constants.LINES);
				LOGGER.info(Constants.NO_TASKS);
				LOGGER.info(Constants.LINES);
			}
		}

		public void readTask() {
			int taskID = taskValidInputs.taskIDInput();
			try {
				Task task=taskSchedule.readTask(taskID);
				LOGGER.info(Constants.TASK_LIST);
				LOGGER.info(Constants.TASK_TABLE_HEADING);
				LOGGER.info(String.format("%10s | %15s | %20s | %20s | %15s | %15s",task.getId(),task.getTitle(),dateFormat.format(task.getStartTime()),dateFormat.format(task.getEndTime()),task.getStatus()));
			}
			catch(TaskIDNotFoundException tnfException){
				
			}
		}


		public void modifyTask() {
			int taskID = taskValidInputs.taskIDInput();
			try {
				Task task=taskSchedule.readTask(taskID);
				int choice;
				boolean flag = true;
				while (flag) {
						choice = taskValidInputs.modifyChoiceInput();
						switch(choice) {
						case 1:
							String title=taskValidInputs.titleInput();
							LOGGER.info(taskSchedule.modifyTaskTitle(task,title));
							break;
						case 2:
							LocalDateTime startTime;
							while(true){
								startTime = taskValidInputs.startTimeInput();
								try {
									LOGGER.info(taskSchedule.modifyTaskStartTime(task,startTime));
									break;
								}
								catch(DateTimeConflictException e) {
									LOGGER.warn(Constants.RE_ENTER_THE_DATA);
								}
							}
							break;
						case 3:
							LocalDateTime endTime;
							while(true){
								endTime = taskValidInputs.endTimeInput(task.getStartTime());
								try {
									LOGGER.info(taskSchedule.modifyTaskEndTime(task,endTime));
									break;
								}
								catch(DateTimeConflictException e) {
									LOGGER.warn(Constants.RE_ENTER_THE_DATA);
								}
							}
							break;
						case 4:
							String status=taskValidInputs.statusInput();
							LOGGER.info(taskSchedule.modifyTaskStatus(task,status));
							break;
						case 5:
							flag=false;
							break;
						default:
							LOGGER.info(Constants.PLEASE_ENTER_A_VALID_CHOICE);
						}
				}
			}
			catch(TaskIDNotFoundException tnfException){}
		}

		
		public void deleteTasks() {
			int id = taskValidInputs.taskIDInput();
			try {
				String result=taskSchedule.deleteTasks(id);
				LOGGER.info(result);
			}
			catch(TaskIDNotFoundException tnfe) {}

		}
		
		public void addNotes() {
			int id = taskValidInputs.taskIDInput();
			String note = taskValidInputs.noteInput();
				try {
					String result=noteCRUD.addNotes(id, note);
					LOGGER.info(result);
				}
			catch(TaskIDNotFoundException tnfe) {}
		
		}
		
		public void printNotes() {
			LOGGER.info(Constants.NOTES_LIST);
			LOGGER.info(Constants.NOTES_TABLE_HEADING);
			try {
				List<Note> notesList=noteCRUD.printNotes();
				for (Note note : notesList) {
					LOGGER.info(Constants.LINES);
					String noteDetails = String.format("%10s | %20s | %10s ", note.getId(),note.getNotes(),note.getTask().getId());
					LOGGER.info(noteDetails);
				}
				LOGGER.info(Constants.LINES);
			}
			catch(EmptyListException ele) {
				LOGGER.info(Constants.LINES);
				LOGGER.info(Constants.NO_NOTES);
				LOGGER.info(Constants.LINES);
			}
		}
			
			public void modifyNote() {
				int noteID = taskValidInputs.noteIDInput();
				String noteInput=taskValidInputs.noteInput();
				try {
					String result=noteCRUD.modifyNote(noteID,noteInput);
					LOGGER.info(result);
				}
				catch(NoteIDNotFoundException nidnfe) {}
			}
			
			public void deleteNote() {
				int noteID = taskValidInputs.noteIDInput();
				try {
					String result=noteCRUD.deleteNote(noteID);
					LOGGER.info(result);
				}
				catch(NoteIDNotFoundException nidnfe) {}
			}
	}
