package com.castellarmartinez.todoapi.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.castellarmartinez.todoapi.domain.Task;
import com.castellarmartinez.todoapi.domain.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc
class TaskControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private TaskRepository repository;

	@Test
	@DisplayName("It should successfully add a new task")
	void testCreateTaskValid() throws Exception {
		Task taskToCreate = new Task("Test Task", "Test Description");
		Mockito.when(repository.save(Mockito.any())).thenReturn(taskToCreate);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(taskToCreate)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Description"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.state").value("PENDING"));
	}

	@Test
	@DisplayName("It should fail to add a new task due to invalid input")
	void testCreateTaskInvalid() throws Exception {
		Task invalidTask = new Task();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidTask)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").exists());
	}
}
