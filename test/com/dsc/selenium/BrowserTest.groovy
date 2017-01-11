package com.dsc.selenium

import com.dsc.selenium.ui.Link
import com.dsc.selenium.util.Util


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
}
