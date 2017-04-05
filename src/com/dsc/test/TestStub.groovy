/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test

import org.apache.log4j.Logger
import org.junit.Rule
import org.junit.rules.TestName

import com.dsc.util.Log

import spock.lang.Specification
/**
 * @Author alex
 * @CreateTime 01.04.2017 16:36:36
 * @Version 1.0
 * @Since 1.0
 */
class TestStub  extends Specification
{
	@Rule TestName name = new TestName()
	private static Logger log = Logger.getLogger(Log.class.getName())

	def _(def message) {
		if( null==message || "".equals(message))
			Log.info("😼 😤  👿  🙁   懒鬼，你都没输given/when/then后的内容，叫我打印个球")//s,行号：$lineNumber"
		else
			Log.info("⏩⏩⏩⏩⏩ 💨  "+message)

		true
	}

	protected String testCase(){
		return  String.join("_", name.methodName.replaceAll("[^a-zA-Z0-9]", "_"))
	}

	//	def setupSpec() {
	//		Log.info(String.format("\nTest****%s**************************started",this.class.simpleName))
	//	}
	//
	//	def setup() {
	//		Log.info(String.format("Case----%s-----------------------",testCase()))
	//	}
	//	def cleanup() {
	//		log.info("")
	//	}

	//	StackTraceElement getStackFrame(String debugMethodName) {
	//		def ignorePackages = [
	//			'sun.',
	//			'java.lang',
	//			'org.codehaus',
	//			'groovy.lang'
	//		]
	//		StackTraceElement frame = null
	//		Throwable t = new Throwable()
	//		t.stackTrace.eachWithIndex { StackTraceElement stElement, int index ->
	//			if (stElement.methodName.contains(debugMethodName)) {
	//				int callerIndex = index + 1
	//				while (t.stackTrace[callerIndex].isNativeMethod() ||
	//				ignorePackages.any { String packageName ->
	//					t.stackTrace[callerIndex].className.startsWith(packageName)
	//				}) {
	//					callerIndex++
	//				}
	//				frame = t.stackTrace[callerIndex]
	//				return
	//			}
	//		}
	//		frame
	//	}
	//
	//	int getLineNumber() {
	//		getStackFrame('getLineNumber')?.lineNumber ?: -1
	//	}
	//
	//	String getFileName() {
	//		getStackFrame('getFileName')?.fileName
	//	}
	//
	//	String getMethodName() {
	//		getStackFrame('getMethodName')?.methodName
	//	}
	//
	//	def getCurrentMethodName(){
	//		def marker = new Throwable()
	//		return StackTraceUtils.sanitize(marker).stackTrace[1].methodName
	//	}
	//
	//	def helloFun(){
	//		println( getCurrentMethodName() )
	//	}
	//
	//	def cleanupSpec() {
	//	}
}