package com.castellarmartinez.todoapi.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {

	Optional<Task> findById(long id);

	void deleteById(long id);

	@Modifying
	@Query("UPDATE Task t SET t.state = :newState WHERE t.id = :id")
	void updateTaskState(long id, String newState);
}
