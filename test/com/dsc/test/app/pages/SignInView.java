/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pages;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppComposite;
import com.dsc.test.web.ui.Link;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @Author alex
 * @CreateTime 24.02.2017 16:53:36
 * @Version 1.0
 * @Since 1.0
 */
public class SignInView extends AppComposite
{
	@FindBy(id = "com.restwla.z88ab174d:id/customlabel_cell_horizontal_house")
	Link contentPolicy;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/customlabel_cell_horizontal_house")
	List<MobileElement> navigateItems;// =context().findElemById("com.restwla.z88ab174d:id/customlabel_cell_horizontal_house");

	/**
	 * @param context
	 * @param id
	 */
	public SignInView(App<?, ?> context)
	{
		super(context, context.findElemByXpath("//*"));
		ensureAvailable();
	}

	public ContentPolicyView clickContentPolicy(){
		contentPolicy.click();
		return new ContentPolicyView(context());
	}

	public SignUpWithEmailView clickSignUpWithEmail()
	{
		navigateItems.get(3).click();
		return new SignUpWithEmailView(context());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		contentPolicy.ensureAvailable();
	}
}