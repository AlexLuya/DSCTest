/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.dsc.test.common.Context;
import com.dsc.test.common.pagefactory.UIObjectDecorator;
import com.google.common.collect.Lists;

/**
 * @Author alex
 * @CreateTime Jan 5, 2015 11:15:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class Widget<H extends Context<? ,? extends WebDriver>> extends UIObject
{
	public Widget(H context, String containerId)
	{
		this(context, context.findElemById(containerId));
	}

	public Widget(H context,  UIObject wrapee){
		this(context,wrapee.element());
	}

	public Widget(H context, WebElement wrapee)
	{
		super(context, wrapee);
		// initialize all fields
		PageFactory.initElements(new UIObjectDecorator(context(),this.element()), this);
	}

	/**
	 * NP not elegant
	 *
	 * @return
	 */
	public List<UIObject> children()
	{
		List<UIObject> children = Lists.newArrayList();

		// boxing as UIObject
		for (WebElement elem : wrapee.findElements(By.xpath("*")))
		{
			children.add(new UIObject(context(), elem));
		}

		return children;
	}

	@SuppressWarnings("unchecked")
	@Override
	public H context(){
		return (H) super.context();
	}

	protected UIObject child(int index)
	{
		return children().get(index);
	}

	protected UIObject child(String id)
	{
		for (UIObject child : children())
		{
			if (id.equals(child.id()))
			{
				return child;
			}
		}
		return null;
	}

	protected WebElement childElem(int index)
	{
		return child(index).getWrapped();
	}

	protected WebElement childElem(String id)
	{
		return child(id).getWrapped();
	}

	protected WebElement childElement(int index)
	{
		return child(index).element();
	}

	/**
	 * @return
	 */
	protected int childrenCount()
	{
		return children().size();
	}
}
