package com.dsc.tool.spock.extractor.domain

//Alex Luya commented this annotation,added constructor due to:
//http://stackoverflow.com/questions/30641790/groovy-generated-constructor-missing-during-compile
//https://issues.apache.org/jira/browse/GROOVY-7593?page=com.atlassian.jira.plugin.system.issuetabpanels:all-tabpanel
//@Immutable
class Statement {
	Block block
	String description

	/**
	 * @param block
	 * @param description
	 */
	public Statement(Block block, String description)
	{
		this.block = block
		this.description = description
	}
}
