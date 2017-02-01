/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import static com.dsc.test.web.util.Util.wrap;
import static java.lang.String.format;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dsc.test.web.Browser;
import com.google.common.collect.Lists;

/**
 * The Class ListBox.
 *
 * @Author alex
 * @CreateTime Dec 30, 2014 9:08:50 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ListBox extends AbstractValidable
{

	/**
	 * Instantiates a new list box.
	 *
	 * @param browser
	 *            the browser
	 * @param wrapee
	 *            the wrapee
	 */
	public ListBox(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/**
	 * Ensure selected item is.
	 *
	 * @param level
	 *            the level
	 * @deprecated replaced by {@link #ensureSelectedOptionIs(int)}
	 */
	@Deprecated
	public void ensureSelectedItemIs(int level)
	{
		ensureSelectedOptionIs(Integer.toString(level));
	}

	/**
	 * Ensure selected item is.
	 *
	 * @param itemId
	 *            the item id
	 * @deprecated replaced by {@link #ensureSelectedOptionIs(java.lang.String)}
	 */
	@Deprecated
	public void ensureSelectedItemIs(String itemId)
	{
		ensureSelectedOptionIs(itemId);
	}

	/**
	 * Ensure selected item is.
	 *
	 * @param level
	 *            the level
	 */
	public void ensureSelectedOptionIs(int level)
	{
		ensureSelectedOptionIs(Integer.toString(level));
	}

	/**
	 * Ensure selected item is.
	 *
	 * @param optionId
	 *            the item id
	 */
	public void ensureSelectedOptionIs(String optionId)
	{
		if (!optionId.equals(selectedOptionId()))
		{
			throw new IllegalStateException(
					String.format("%s's selected item id isn't '%s' but '%s'", id(), optionId, selectedOptionId()));
		}
	}

	/**
	 * Options.
	 *
	 * @return the list
	 */
	public List<Option> options()
	{
		List<Option> options = Lists.newArrayList();

		for (WebElement elem : self().getOptions())
		{
			options.add(new Option(browser, elem));
		}

		return options;
	}

	/**
	 * Select by id.
	 *
	 * @param optionId
	 *            the option id
	 */
	public void selectById(int optionId)
	{
		selectById(Integer.toString(optionId));
	}

	/**
	 * Select by id.
	 *
	 * @param optionId
	 *            the option id
	 */
	public void selectById(String optionId)
	{
		for (int i = 0, len = options().size(); i < len; i++)
		{
			if (options().get(i).idEquals(optionId))
			{
				click();
				self().selectByIndex(i);
				break;
			}
		}
	}

	/**
	 * Select by value.
	 *
	 * @param value
	 *            the value
	 */
	public void selectByValue(int value)
	{
		selectByValue(Integer.toString(value));
	}

	/**
	 * Select by value.
	 *
	 * @param value
	 *            the value
	 */
	public void selectByValue(String value)
	{
		click();
		self().selectByValue(value);
	}

	/**
	 * Select by visible text.
	 *
	 * @param text
	 *            the text
	 */
	public void selectByVisibleText(String text)
	{
		click();
		self().selectByVisibleText(text);
	}

	/**
	 * Selected option.
	 *
	 * @return the first selected option
	 */
	public Option selectedOption()
	{
		if(self().getAllSelectedOptions().isEmpty())
		{
			throw new IllegalStateException(wrap(format("no option selected in ListBox with id ----------%s---------", id())));
		}

		return new Option(browser, self().getFirstSelectedOption());
	}

	/**
	 * Selected option id.
	 *
	 * @return the id of first selected option
	 */
	public String selectedOptionId()
	{
		return selectedOption().id();
	}

	/**
	 * Selected option index.
	 *
	 * @return the index of first selected option
	 */
	public int selectedOptionIndex()
	{
		return options().indexOf(selectedOption());
	}

	/**
	 * Selected option text.
	 *
	 * @return the text of first selected opiton
	 */
	public String selectedOptionText()
	{
		return selectedOption().text();
	}

	/**
	 * Self.
	 *
	 * @return the self
	 */
	private Select self()
	{
		return new Select(wrapee);
	}
}