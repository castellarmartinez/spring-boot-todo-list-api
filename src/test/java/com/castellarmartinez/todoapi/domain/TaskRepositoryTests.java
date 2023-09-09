package com.castellarmartinez.todoapi.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TaskRepositoryTests {
	@Autowired
	private TaskRepository repository;

	@Test
	@DisplayName("It should successful save a task")
	void saveOwner() {
		// En la tabla de datos el id es auto incremental y empieza desde el valor 1
		long id = 1L;
		repository.save(new Task("My task", "Stuff to be done", "PENDING"));
		Optional<Task> createdTask = repository.findById(id);

		assertThat(createdTask.isPresent()).isTrue();
	}
}
