package com.epam.taskManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.taskManagement.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

}
