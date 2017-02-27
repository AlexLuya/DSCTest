/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppComposite;

import io.appium.java_client.MobileElement;

/**
 * @Author alex
 * @CreateTime 24.02.2017 17:27:28
 * @Version 1.0
 * @Since 1.0
 */
public class NavigateItem extends AppComposite
{
	/**
	 * @param context
	 * @param wrapee
	 */
	public NavigateItem(App<?, ?> context, MobileElement wrapee)
	{
		super(context, wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		// HP
	}

}
