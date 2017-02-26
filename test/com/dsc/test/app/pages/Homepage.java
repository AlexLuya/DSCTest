/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pages;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.SlideShow;
import com.dsc.test.app.ui.base.AppComposite;
import com.dsc.test.common.ui.Button;
import com.dsc.test.common.ui.Icon;

import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @Author alex
 * @CreateTime 16.02.2017 14:23:11
 * @Version 1.0
 * @Since 1.0
 */
public class Homepage extends AppComposite
{
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/button_callout_main")
	Button	callUsButton;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/button_callout_text")
	Button	callUsButtonText;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/button_callout_iconfont")
	Button	callUsIcon;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/menu_profile_place_holder")
	Icon	me;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/scroll_view_header")
	SlideShow	slideShow;

	public Homepage(App<?, ?> app)
	{
		super(app, app.findElemByXpath("//*"));
		ensureAvailable();
	}

	public CallUsView clickCallUs(){

		callUsButton.click();

		return new CallUsView(context());
	}

	public SignInView clickMe(){

		me.click();

		return new SignInView(context());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		slideShow.ensureAvailable();
		callUsButton.ensureAvailable();
		callUsButtonText.ensureAvailable();
		callUsIcon.ensureAvailable();
	}
}