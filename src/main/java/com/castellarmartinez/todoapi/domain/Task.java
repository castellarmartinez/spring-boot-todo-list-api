package com.castellarmartinez.todoapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", length = 512)
	private String description;

	@Column(name = "state", nullable = false)
	private String state;

	public Task() {
	}

	public Task(String name, String description) {
		this.title = name;
		this.description = description;
		this.state = "PENDING";
	}

	public Task(String name, String description, String state) {
		this.title = name;
		this.description = description;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", state=" + state + "]";
	}

}
