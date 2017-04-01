/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test

import spock.lang.Specification

/**
 * @Author alex
 * @CreateTime 01.04.2017 16:36:36
 * @Version 1.0
 * @Since 1.0
 */
class TestStub  extends Specification
{
	def _(def message) {
		if( null==message || "".equals(message)){
			//			println "懒鬼，你没输given|when|then后的内容，叫我打印个球 😤 👿"
			//			println "懒鬼，你没输given-when-then后的内容，叫我打印个球?????"
			println "懒鬼，你都没输given/when/then后的内容，叫我打印个球  😤  👿  🙁"
			//			println "懒鬼，你没输given\\when\\then后的内容，叫我打印个球?????"
		}else
			println message
		true
	}
}
