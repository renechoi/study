package org.example.javaconcurrencyproblem.cucumber.utils;

import org.example.javaconcurrencyproblem.testhelpers.data.DatabaseCleanupExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java8.En;

/**
 * @author : Rene Choi
 * @since : 2024/02/24
 */
public class DatabaseCleanupStepDef implements En {

	@Autowired
	private DatabaseCleanupExecutor databaseCleanupExecutor;

	public DatabaseCleanupStepDef() {
		Before(() -> databaseCleanupExecutor.execute());
	}
}

