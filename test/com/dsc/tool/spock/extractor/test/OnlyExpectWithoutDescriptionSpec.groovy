package com.dsc.tool.spock.extractor.test

import spock.lang.Specification

class OnlyExpectWithoutDescriptionSpec extends Specification {
	def "adding test"() {
		expect:
		2 + 10 == 12
	}
}