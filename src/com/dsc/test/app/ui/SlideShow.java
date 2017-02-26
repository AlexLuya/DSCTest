/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.app.App;

/**
 * @Author alex
 * @CreateTime 23.02.2017 17:17:42
 * @Version 1.0
 * @Since 1.0
 */
public class SlideShow extends Carousel
{
	/**
	 * @param context
	 * @param wrapee
	 */
	public SlideShow(App<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

}
