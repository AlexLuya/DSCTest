/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
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
 * @CreateTime 24.02.2017 17:32:06
 * @Version 1.0
 * @Since 1.0
 */
public class SignUpWithEmailView extends AppComposite
{
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/name")
	TextBox	email;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/email")
	TextBox	name;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/password")
	TextBox	password;
	@AndroidFindBy(id = "com.restwla.z88ab174d:id/submit_button")
	Button	signUp;

	/**
	 * @param context
	 * @param id
	 */
	public SignUpWithEmailView(App<?, ?> context)
	{
		super(context, context.findElemByXpath("//*"));
		ensureAvailable();
	}

	public void clickSignUp()
	{
		signUp.click();
	}

	public String email()
	{
		return email.text();
	}

	public void inputEmail(String text)
	{
		email.input(text);
	}

	public void inputName(String text)
	{
		name.input(text);
	}

	public void inputPassword(String text)
	{
		password.input(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		email.ensureAvailable();
		name.ensureAvailable();
		password.ensureAvailable();
		signUp.ensureAvailable();
	}

}
