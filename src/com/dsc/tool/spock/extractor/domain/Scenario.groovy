package com.dsc.tool.spock.extractor.domain

import java.util.List
import java.util.Set

import groovy.transform.Immutable

//Alex Luya commented this annotation,added constructor due to:
//http://stackoverflow.com/questions/30641790/groovy-generated-constructor-missing-during-compile
//https://issues.apache.org/jira/browse/GROOVY-7593?page=com.atlassian.jira.plugin.system.issuetabpanels:all-tabpanel
//@Immutable
class Scenario {
	String name
	List<Statement> statements
	Set<String> issues
	Set<String> links
	Ignored ignored	

	/**
	 * @param name
	 * @param statements
	 * @param issues
	 * @param links
	 * @param ignored
	 */
	public Scenario(String name, List<Statement> statements, Set<String> issues, Set<String> links, Ignored ignored)
	{
		this.name = name;
		this.statements = statements;
		this.issues = issues;
		this.links = links;
		this.ignored = ignored;
	}

	boolean isValid() {
		!statements.isEmpty()
	}
}
