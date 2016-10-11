/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dsc.selenium.Browser;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 9:08:50 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ListBox extends AbstractValidable
{

	/**
	 * @param wrapee
	 */
	public ListBox(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/**
	 * @param level
	 */
	public void ensureSelectedItemIs(int level)
	{
		ensureSelectedItemIs(Integer.toString(level));
	}
	public void ensureSelectedItemIs(String itemId)
	{
		if(!itemId.equals(selectedItemId()))
		{
			throw new IllegalStateException(String.format("%s's selected item id isn't '%s' but '%s'", id(), itemId, selectedItemId()));
		}
	}

	public void selectById(int optionId)
	{
		selectById(Integer.toString(optionId));
	}

	public void selectById(String optionId)
	{
		for (int i = 0, len = options().size(); i < len; i++)
		{
			if (id(options().get(i)) == optionId)
			{
				self().selectByIndex(i);
				break;
			}
		}
	}

	/**
	 * @param birthYear
	 */
	public void selectByValue(int value)
	{
		selectByValue(Integer.toString(value));
	}

	public void selectByValue(String value)
	{
		self().selectByValue(value);
	}

	public void selectByVisibleText(String text)
	{
		self().selectByVisibleText(text);
	}


	/**
	 * @return
	 */
	private List<WebElement> options()
	{
		return self().getOptions();
	}

	/**
	 * @return
	 */
	private String selectedItemId()
	{
		return id(self().getFirstSelectedOption());
	}

	/**
	 * @return
	 */
	private Select self()
	{
		return new Select(wrapee);
	}
}
