/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common;

import com.dsc.test.app.App;
import com.dsc.test.app.android.Android;
import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime 15.02.2017 12:55:01
 * @Version 1.0
 * @Since 1.0
 */
public class ContextFactory
{
	public static App<?, ?> app()
	{
		return new Android();
	}

	public static Browser browser()
	{
		return Browser.chrome();
	}
}
