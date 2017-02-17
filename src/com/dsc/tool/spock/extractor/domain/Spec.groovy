package com.dsc.tool.spock.extractor.domain

//Alex Luya commented this annotation,added constructor due to:
//http://stackoverflow.com/questions/30641790/groovy-generated-constructor-missing-during-compile
//https://issues.apache.org/jira/browse/GROOVY-7593?page=com.atlassian.jira.plugin.system.issuetabpanels:all-tabpanel
//@Immutable
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
	public Spec(String name, String title, String description, Set<Class> subjects, List<Scenario> scenarios, Set<String> issues,
	Set<String> links, Ignored ignored)
	{
		super()
		this.name = name
		this.title = title
		this.description = description
		this.subjects = subjects
		this.scenarios = scenarios
		this.issues = issues
		this.links = links
		this.ignored = ignored
	}

	boolean isValid() {
		!scenarios.isEmpty()
	}
}



