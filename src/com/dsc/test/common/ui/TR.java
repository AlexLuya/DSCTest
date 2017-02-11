/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.widget.Widget;

/**
 * @Author alex
 * @CreateTime Dec 23, 2016 1:18:18 PM
 * @Version 1.0
 * @Since 1.0
 */
public class TR extends Widget<Context>
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public TR(Context context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	public TD cell(int index){
		return new TD(context(), childElem(index));
	}

}
