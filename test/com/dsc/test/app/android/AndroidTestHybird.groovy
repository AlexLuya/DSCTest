/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android

import com.dsc.test.app.AppTestStub
import com.dsc.test.app.pages.HybirdDemoNative
import com.dsc.test.app.pages.HybirdDemoWeb

import spock.lang.Shared

/**
 * @Author alex
 * @CreateTime Feb 12, 2017 12:12:05 AM
 * @Version 1.0
 * @Since 1.0
 */
public class AndroidTestHybird extends AppTestStub
{
	@Shared HybirdDemoNative nativePage

	def setupSpec(){

		app.deviceName("1501-M02").version("5.1").open("file/WebviewTestArcher.apk")
		app.activity("com.ginkodrop.hybirddemo.view.MainActivity")

		nativePage=new HybirdDemoNative(app)
	}

	def "swith between web and native"(){
		String textInNativeTextBox="text in NATIVE text box"
		String textInWebTextBox="text in WEB text box"

		when:"click [Open web page]"
		HybirdDemoWeb webPage=nativePage.clickOpenWebPage()
		and:"switch to web context"
		app.toWebView()
		and:"set a text to text box"
		webPage.setText(textInWebTextBox)

		then:"get text successfully"
		webPage.getText()==textInWebTextBox

		when:"switch back to native"
		nativePage=webPage.clickOpenNativePage()
		and:"click [Open native page]"
		app.toNative()
		and:"set a text to text box"
		nativePage.setText(textInNativeTextBox)

		then:"get text successfully"
		nativePage.getText()==textInNativeTextBox
	}

}
