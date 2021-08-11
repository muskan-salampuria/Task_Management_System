package com.epam.taskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.epam.taskManagement.view.MainConsole;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.epam.taskManagement"})
public class TaskManagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TaskManagerApplication.class, args);
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TaskManagerApplication.class);
		context.getBean(MainConsole.class).execute();
	}
}
