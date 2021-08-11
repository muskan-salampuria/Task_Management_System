package com.epam.taskManagement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.taskManagement.view.MainConsole;

@Configuration
@ComponentScan(basePackages= {"com.epam.taskManagement"})
public class App {
	
	public static void main(String args[]) {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        context.getBean(MainConsole.class).execute();
        
	}
}
