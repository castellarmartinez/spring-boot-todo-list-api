package com.castellarmartinez.todoapi.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.castellarmartinez.todoapi.domain.Task;

class ValidatorTests {
	@Test
	@DisplayName("It should fail when validating a task due to null valeu")
	void testIsValidTaskNullValue() {
		Task nullTask = null;
		assertFalse(Validator.isValidTask(nullTask));
	}

	@Test
	@DisplayName("It should fail when validating a task due to missing title")
	void testIsValidTaskMissingTitle() {
		Task taskWithoutTitle = new Task(null, "Description", "PENDING");
		assertFalse(Validator.isValidTask(taskWithoutTitle));
	}

	@Test
	@DisplayName("It should fail when validating a task due to empty title")
	void testIsValidTaskEmptyTitle() {
		Task taskWithoutTitle = new Task("", "Description", "PENDING");
		assertFalse(Validator.isValidTask(taskWithoutTitle));
	}

	@Test
	@DisplayName("It should fail when validating a task due to missing description")
	void testIsValidTaskMissingDescription() {
		Task taskWithoutDescription = new Task("Title", null, "PENDING");
		assertFalse(Validator.isValidTask(taskWithoutDescription));
	}

	@Test
	@DisplayName("It should fail when validating a task due to empty description")
	void testIsValidTaskEmptyDescription() {
		Task taskWithoutDescription = new Task("Title", "", "PENDING");
		assertFalse(Validator.isValidTask(taskWithoutDescription));
	}

	@Test
	@DisplayName("It should fail when validating a task due to an invalid state")
	void testIsValidTaskInvalidState() {
		Task taskWithInvalidState = new Task("Title", "Description", "INVALID_STATE");
		assertFalse(Validator.isValidTask(taskWithInvalidState));
	}

	@Test
	@DisplayName("It should validate successful a task with default state (PENDING)")
	void testIsValidTaskValidWithDefaultState() {
		Task validTask = new Task("Valid Task", "Valid Description", "PENDING");
		assertTrue(Validator.isValidTask(validTask));
	}

	@Test
	@DisplayName("It should successfully validate successful a task with provided state")
	void testIsValidTaskValidWithProvidedState() {
		Task validTask = new Task("Valid Task", "Valid Description", "PENDING");
		assertTrue(Validator.isValidTask(validTask));
	}

	@Test
	@DisplayName("It should validate a task state due to a null task")
	void testIsValidTaskStateNullTask() {
		Task nullTask = null;
		assertFalse(Validator.isValidTaskState(nullTask));
	}

	@Test
	@DisplayName("It fail when validate a task state due to invalide state")
	void testIsValidTaskStateInvalidState() {
		Task taskWithInvalidState = new Task(null, null, "INVALID_STATE");
		assertFalse(Validator.isValidTaskState(taskWithInvalidState));
	}

	@Test
	@DisplayName("It fail when validate a task state due null state")
	void testIsValidTaskStateNullState() {
		Task taskWithNullState = new Task(null, null, null);
		assertFalse(Validator.isValidTaskState(taskWithNullState));
	}

	@Test
	@DisplayName("It should successfully validate a task state")
	void testIsValidTaskStateValid() {
		Task validTask = new Task(null, null, "PENDING");
		assertTrue(Validator.isValidTaskState(validTask));
	}
}
