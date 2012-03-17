package de.bitnoise.testing.config;

import java.util.List;

/**
 * Interface for Environment providers. These are used to detect the Environment this your Tests are currently running in.
 */
public interface EnvironmentProvider {

	/**
	 * Detects and creates a list of environments
	 * 
	 * @return A List of Environment Objects which should be loaded. does never return {@code null} and does not throws any exceptions.
	 */
	List<Environment> getDetectedEnvironments();
}
