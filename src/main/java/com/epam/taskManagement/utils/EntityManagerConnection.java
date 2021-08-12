package com.epam.taskManagement.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EntityManagerConnection {

	@Bean
	@Primary
	public EntityManager getEntityManagerConnection() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-local-mysql");
		return entityManagerFactory.createEntityManager();
	}
}
