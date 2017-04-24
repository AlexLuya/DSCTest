/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app

import com.dsc.test.TestStub
import com.dsc.test.common.ContextFactory

/**
 * @Author alex
 * @CreateTime Feb 1, 2017 12:24:43 PM
 * @Version 1.0
 * @Since 1.0
 */
public class AppTestStub extends TestStub
{
	App app=ContextFactory.app()

	def cleanup(){
		app.close()
	}
}