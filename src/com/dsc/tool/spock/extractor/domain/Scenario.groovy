package com.dsc.tool.spock.extractor.domain

import groovy.transform.Immutable

@Immutable
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
	//	public Scenario(String name, List<Statement> statements, Set<String> issues, Set<String> links, Ignored ignored)
	//	{
	//		this.name = name;
	//		this.statements = statements;
	//		this.issues = issues;
	//		this.links = links;
	//		this.ignored = ignored;
	//	}

	boolean isValid() {
		!statements.isEmpty()
	}
}
