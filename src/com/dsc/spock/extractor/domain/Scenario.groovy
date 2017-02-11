package com.dsc.spock.extractor.domain

import java.util.List
import java.util.Set

import groovy.transform.Immutable

@Immutable
class Scenario {
	String name
	List<Statement> statements
	Set<String> issues
	Set<String> links
	Ignored ignored


	boolean isValid() {
		!statements.isEmpty()
	}
}
