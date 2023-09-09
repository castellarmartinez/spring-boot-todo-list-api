package com.castellarmartinez.todoapi.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castellarmartinez.todoapi.domain.Task;
import com.castellarmartinez.todoapi.domain.TaskRepository;
import com.castellarmartinez.todoapi.utils.ErrorMessage;
import com.castellarmartinez.todoapi.utils.Validator;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	private final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskRepository repository;

	@PostMapping
	public ResponseEntity<?> createTask(@RequestBody Task task) {
		logger.info("POST /api/tasks");
		logger.info("body: {}", task);

		if (Validator.isValidTask(task)) {
			Task savedTask = repository.save(task);
			return ResponseEntity.ok(savedTask);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(
				"Invalid task data. A value for 'title', 'description' and 'state'['PENDING', 'COMPLETED'] must be provide"));

	}

	@GetMapping
	public ResponseEntity<Iterable<Task>> getTasks() {
		logger.info("GET /api/tasks");

		Iterable<Task> tasks = repository.findAll();
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable String id) {
		logger.info("GET /api/tasks/{}", id);

		try {
			long taskId = Long.parseLong(id, 10);
			Optional<Task> optionalTask = repository.findById(taskId);

			if (optionalTask.isPresent()) {
				return ResponseEntity.ok(optionalTask.get());
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Task not found"));
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Task id must be a number"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTaskById(@PathVariable String id, @RequestBody Task newTask) {
		logger.info("PUT /api/tasks/{}", id);
		logger.info("body: {}", newTask);

		try {
			long taskId = Long.parseLong(id, 10);
			Optional<Task> optionalTask = repository.findById(taskId);

			if (!Validator.isValidTaskState(newTask)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ErrorMessage("Value for 'state' must be 'PEDING' or 'COMPLETED'"));
			}

			if (optionalTask.isPresent()) {
				repository.updateTaskState(taskId, newTask.getState());
				return ResponseEntity.ok("Task state update to " + newTask.getState());
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Task not found"));
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Task id must be a number"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTaskById(@PathVariable String id) {
		logger.info("DELETE /api/tasks/{}", id);

		try {
			long taskId = Long.parseLong(id, 10);
			Optional<Task> optionalTask = repository.findById(taskId);

			if (optionalTask.isPresent()) {
				repository.deleteById(taskId);
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Task not found"));
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Task id must be a number"));
		}
	}
}
