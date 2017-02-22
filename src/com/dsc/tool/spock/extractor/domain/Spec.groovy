package com.dsc.tool.spock.extractor.domain

import groovy.transform.Immutable

@Immutable
class Spec {
	String name
	String title
	String description
	Set<Class> subjects
	List<Scenario> scenarios = []
	Set<String> issues
	Set<String> links
	Ignored ignored

	/**
	 * @param name
	 * @param title
	 * @param description
	 * @param subjects
	 * @param scenarios
	 * @param issues
	 * @param links
	 * @param ignored
	 */
	//	public Spec(String name, String title, String description, Set<Class> subjects, List<Scenario> scenarios, Set<String> issues,
	//	Set<String> links, Ignored ignored)
	//	{
	//		super()
	//		this.name = name
	//		this.title = title
	//		this.description = description
	//		this.subjects = subjects
	//		this.scenarios = scenarios
	//		this.issues = issues
	//		this.links = links
	//		this.ignored = ignored
	//	}

	boolean isValid() {
		!scenarios.isEmpty()
	}
}



