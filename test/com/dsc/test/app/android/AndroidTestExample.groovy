/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android

import com.dsc.test.ContextFactory
import com.dsc.test.app.AppTestStub
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
	@Shared Android android=ContextFactory.app()
	@Shared Homepage homepage

	def setupSpec(){
		android.deviceName("Galaxy S4").platform("5.0.1").open("file/Hybrid_v1.8.1.apk")
		//UIAutomatorViewer will tell this
		android.pkg("com.restwla.z88ab174d")

		homepage=new Homepage(android)
	}


	def "swipe left"(){
		given:
		android.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:""
		int res=2+3

		then:
		res==5
	}

	def "swipe right"(){
		given:
		android.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:""
		int res=2+3

		then:
		res==5
	}

	def "upload"(){
		given:
		android.activity("com.application.zomato.bake.prototype.activities.HomeActivityCommon")

		when:""
		int res=2+3

		then:
		res==5
	}
}
