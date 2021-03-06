/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android

import com.dsc.test.app.AppTestStub
import com.dsc.test.app.pages.CallUsView
import com.dsc.test.app.pages.Homepage
import com.dsc.test.app.pages.SignInView
import com.dsc.test.app.pages.SignUpWithEmailView

import spock.lang.Shared

/**
 * @Author alex
 * @CreateTime Feb 12, 2017 12:12:05 AM
 * @Version 1.0
 * @Since 1.0
 */
public class AndroidTestComplex extends AppTestStub
{
	@Shared Homepage homepage

	def setup(){
		//		app.deviceName("ZTEG719C").version("4.4.4").open("file/Hybrid_v1.8.1.apk")

		app.deviceName("1501-M02").version("5.1").open("file/Hybrid_v1.8.1.apk")
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")
		//open installed app
		//		app.deviceName("1501-M02").version("5.1").pkgOrBundle("com.restwla.z88ab174d").open("com.application.zomato.bake.prototype.activities.SplashActivity")

		homepage=new Homepage(app)
	}


	def "swipe left"(){
		given:
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:"swipe left"
		homepage.slideShow.swipeLeft(300)

		then:
		true
		//check new pointer position
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
		//check new pointer position
	}

	def "swipe up"(){
		//check new pointer position
	}

	def "swipe down"(){
		//check new pointer position
	}

	def "swipe diagonal"(){
		//check new pointer position
	}

	def "click"(){
		given:
		app.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:
		CallUsView callUsView=homepage.clickCallUs()

		then:
		callUsView.presented()==true
	}

	def "check text"(){
		String email = "email@domain.com"

		when:
		SignUpWithEmailView signUpView = homepage.clickMe().clickSignUpWithEmail()
		signUpView.inputEmail(email)
		and:""
		app.hideKeyboard()

		then:
		signUpView.email()==email
	}

	def "switch between native and web"(){
		String email = "email@domain.com"

		when:"jump to signup view"
		SignInView signInView = homepage.clickMe()
		and:"click [Content policy]"
		signInView.clickContentPolicy()
		and:"switch to web view"
		app.hideKeyboard()

		then:
		signInView.email()==email
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
