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
	def "test"(){
		given:_ ""
		int i=1,j=2
		println "given"

		when:_ "when"
		int l=i+j
		println "when"

		then:_ "then"
		l==3
		println "then"
	}
}
