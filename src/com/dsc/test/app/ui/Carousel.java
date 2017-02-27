/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppUIField;

import io.appium.java_client.MobileElement;

/**
 * @Author alex
 * @CreateTime 23.02.2017 17:17:42
 * @Version 1.0
 * @Since 1.0
 */
//carousel可以无休止的往一个一个方向滑动，首尾相接的；slideshow的话有头有尾。
public class Carousel extends AppUIField
{
	/**
	 * @param context
	 * @param wrapee
	 */
	public Carousel(App<?, ?> context, MobileElement wrapee)
	{
		super(context, wrapee);
	}

	// make it visible
	@Override
	public void swipeLeft(int distance)
	{
		super.swipeLeft(distance);
	}

	// make it visible
	@Override
	public void swipeRight(int distance)
	{
		super.swipeRight(distance);
	}
}
