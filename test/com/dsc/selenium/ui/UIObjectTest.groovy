/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui

import com.dsc.selenium.TestStub

/**
 * @Author alex
 * @CreateTime Jun 14, 2016 3:02:44 PM
 * @Version 1.0
 * @Since 1.0
 */
class UIObjectTest extends TestStub {
	static final String BAIDU_MAP="http://map.baidu.com/"

	def setup(){
		browser.open(BAIDU_MAP)
	}

	def "dragAndDrop"(){
		UIObject object=new UIObject(browser, browser.findElemByTag("canvas"))

		when:"perform draging and dropping"
		object.dragAndDrop(-500, 500)

		then:
		true
	}
	//
	//	def "doubleClick"(){
	//		UIObject object=new UIObject(browser, browser.findElemByTag("canvas"))
	//
	//		when:"perform draging and dropping"
	//		object.doubleClick()
	//
	//		then:
	//		true
	//	}
}
