package com.dsc.selenium


public class BrowserTest extends TestStub {

	static final String TEST_PAGE="https://www.baidu.com/"

	def setup(){
		browser.open(TEST_PAGE)
	}

	def "find by id,but corresponded element doesn't exist"(){
		when:"find a non-existed element"
		browser.findElemById("wild id - sdfasdfljsdorjw23")

		then:thrown(RuntimeException)
	}
}
