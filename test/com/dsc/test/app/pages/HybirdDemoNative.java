/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pages;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppComposite;
import com.dsc.test.common.ui.Button;
import com.dsc.test.common.ui.TextBox;

import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @Author alex
 * @CreateTime 28.03.2017 11:49:44
 * @Version 1.0
 * @Since 1.0
 */
public class HybirdDemoNative extends AppComposite
{
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/button_callout_main")
	Button	openWebPage;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/button_callout_text")
	TextBox	textBox;

	public HybirdDemoNative(App<?, ?> app)
	{
		super(app, app.findElemByXpath("//*"));
		ensureAvailable();
	}

	public HybirdDemoWeb clickOpenWebPage()
	{
		openWebPage.click();
		return new HybirdDemoWeb(context());
	}

	public String getText()
	{
		return textBox.text();
	}

	public void setText(String text)
	{
		textBox.input(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		textBox.ensureAvailable();
		openWebPage.ensureAvailable();
	}
}
