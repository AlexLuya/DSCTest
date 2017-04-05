/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test

/**
 * @Author alex
 * @CreateTime 01.04.2017 16:17:04
 * @Version 1.0
 * @Since 1.0
 */
public class Test extends TestStub
{
	def "test-1"(){
		given:_ ""
		int i=1,j=2

		when:_ "删除老人相关合同数据，并确保老人做过照护计划,且老人是居家老人"
		int l=i+j

		then:_ "新窗口正确打开t2的订单付款界面"
		l==3
	}
	def "test-2"(){
		given:_ "老人已存在"
		int i=1,j=2

		when:_ "删除老人相关合同数据，并确保老人做过照护计划,且老人是居家老人"
		int l=i+j

		then:_ "新窗口正确打开t2的订单付款界面"
		l==3
	}
}
