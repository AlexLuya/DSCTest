/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android

import com.dsc.test.app.AppTestStub
import com.dsc.test.app.pages.CallUsView
import com.dsc.test.app.pages.Homepage

import spock.lang.Shared

/**
 * @Author alex
 * @CreateTime Feb 12, 2017 12:12:05 AM
 * @Version 1.0
 * @Since 1.0
 */
public class AndroidTestExample extends AppTestStub
{
	@Shared Homepage homepage

	def setupSpec(){
		app.deviceName("1501-M02").version("5.1").open("file/Hybrid_v1.8.1.apk")
		//		app.deviceName("1501-M02").version("5.1").activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon").open("com.restwla.z88ab174d")

		homepage=new Homepage(app)
	}

	def "swipe left"(){
		given:
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:"swipe left"
		homepage.slideShow.swipeLeft(300)

		then:
		true
	}

	def "swipe right"(){
		given:
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:"swipe left"
		homepage.slideShow.swipeLeft(300)
		and:"swipe back"
		homepage.slideShow.swipeRight(300)

		then:
		true
	}

	def "swipe up"(){
	}

	def "swipe down"(){
	}

	def "swipe diagonal"(){
	}

	def "click"(){
		given:
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:
		CallUsView callUsView=homepage.clickCallUs()

		then:
		callUsView.presented()==true
	}

	def "hideKeyBoard"(){
		given:
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:
		homepage.clickMe().clickSignUpWithEmail().inputEmail("email@domain.com")
		and:""
		app.hideKeyboard()

		then:
		app.isKeyboardHidden()==true
	}

	def "pullFiles"(){

	}

	def "press"(){
		when:
		app.press(homepage.getWrapped())

		then:
		true
	}

	def "tap"(){
		when:
		app.tap(homepage.getWrapped())

		then:
		true
	}

	def "toNative"(){

	}

	def "toWebView"(){

	}

	def "lock device"(){
		when:
		app.lockDevice(1)

		then:
		true
	}

	def "pull file"(){

	}

	def "unlock scree"(){

	}
}