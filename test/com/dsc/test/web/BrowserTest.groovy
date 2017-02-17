package com.dsc.test.web

import com.dsc.test.ContextFactory
import com.dsc.test.web.ui.Link
import com.dsc.util.Util

import spock.lang.Specification

public class BrowserTest extends Specification {

	static final String TEST_PAGE="https://www.baidu.com/"
	Browser browser=ContextFactory.browser()

	def setup(){
		browser.open(TEST_PAGE)
	}

	def "find by id,but corresponded element doesn't exist"(){
		when:"find a non-existed element"
		browser.findElemById("wild id - sdfasdfljsdorjw23")

		then:thrown(RuntimeException)
	}

	def "switch between tabs"(){
		when:"click link to open a window in new tab"
		new Link(browser,browser.findElemByLinkText("京公网安备11000002000001号")).click()
		and:"switch to popup window"
		browser.switchToPopupWindow()
		and:"switch back to previous window"
		browser.switchBackToPreviousWindow()

		then:"browser do switched back to previous window"
		Util.sleep(3000)
		browser.getCurrentUrl()==TEST_PAGE
	}

	def cleanup(){
		browser.close()
	}
}