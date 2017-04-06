package com.dsc.test.web

import com.dsc.test.web.ui.Link
import com.dsc.util.Util

public class BrowserTest extends WebTestStub {
	static final String TEST_PAGE="https://www.baidu.com/"

	def setup(){
		browser.open(TEST_PAGE)
	}

	def "find by id,but corresponded element doesn't exist"(){
		when:_ "find a non-existed element"
		browser.findElemById("wild id - sdfasdfljsdorjw23")

		then:thrown(RuntimeException)
	}

	def "switch between tabs"(){
		when:_ "click link to open a window in new tab"
		new Link(browser,browser.findElemByLinkText("京公网安备11000002000001号")).click()
		and:_ "switch to popup window"
		browser.switchToPopupWindow()
		and:_ "switch back to previous window"
		browser.switchBackToPreviousWindow()

		then:_ "browser do switched back to previous window"
		Util.sleep(3)
		browser.getCurrentUrl()==TEST_PAGE
	}
}