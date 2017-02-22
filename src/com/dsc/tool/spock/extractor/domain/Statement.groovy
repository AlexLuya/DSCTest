package com.dsc.tool.spock.extractor.domain

import groovy.transform.Immutable

@Immutable
class Statement {
	Block block
	String description

	/**
	 * @param block
	 * @param description
	 */
	//	public Statement(Block block, String description)
	//	{
	//		this.block = block
	//		this.description = description
	//	}
}
