package ru.hzerr.fx.framework.core.context.config.now.sc;

public interface Scanner<T> {

	/**
	 * Perform a scan within the specified base packages.
	 * @param basePackages the packages to check for annotated classes
	 */
	void scan(String... basePackages);
}