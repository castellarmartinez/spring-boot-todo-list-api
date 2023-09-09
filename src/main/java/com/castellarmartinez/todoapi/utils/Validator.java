package com.castellarmartinez.todoapi.utils;

import com.castellarmartinez.todoapi.domain.Task;

public class Validator {
	public static boolean isValidTask(Task task) {
		if (task == null) {
			return false;
		}

		if (task.getState() == null) {
			task.setState("PENDING");
		}

		if (task.getTitle() == null || task.getTitle().isEmpty()) {
			return false;
		}

		if (task.getDescription() == null || task.getDescription().isEmpty()) {
			return false;
		}

		if (!task.getState().equals("PENDING") && !task.getState().equals("COMPLETED")) {
			return false;
		}

		return true;
	}

	public static boolean isValidTaskState(Task task) {
		if (task == null) {
			return false;
		}

		if (task.getState() == null) {
			return false;
		}

		if (!task.getState().equals("PENDING") && !task.getState().equals("COMPLETED")) {
			return false;
		}

		return true;
	}
}
