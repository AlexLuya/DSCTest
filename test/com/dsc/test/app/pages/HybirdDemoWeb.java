/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pages;

import org.openqa.selenium.support.FindBy;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppComposite;
import com.dsc.test.common.ui.Button;
import com.dsc.test.common.ui.TextBox;

/**
 * @Author alex
 * @CreateTime 28.03.2017 11:53:03
 * @Version 1.0
 * @Since 1.0
 */
public class HybirdDemoWeb extends AppComposite
{
	@FindBy(id = "com.restwla.z88ab174d:id/button_callout_main")
	Button	openWebPage;
	@FindBy(id = "com.restwla.z88ab174d:id/button_callout_text")
	TextBox	textBox;

	public HybirdDemoWeb(App<?, ?> app)
	{
		super(app, app.findElemByXpath("//*"));
		ensureAvailable();
	}

	public HybirdDemoNative clickOpenNativePage()
	{
		openWebPage.click();
		return new HybirdDemoNative(context());
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
