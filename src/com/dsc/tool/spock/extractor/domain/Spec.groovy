package com.dsc.tool.spock.extractor.domain

import java.util.List
import java.util.Set

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

	boolean isValid() {
		!scenarios.isEmpty()
	}
}



