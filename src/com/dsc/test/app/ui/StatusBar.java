/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppComposite;
import com.dsc.test.common.ui.base.GeneralUIField;

/**
 * @Author alex
 * @CreateTime 25.02.2017 22:32:27
 * @Version 1.0
 * @Since 1.0
 */
public class StatusBar extends AppComposite
{

	/**
	 * @param context
	 * @param object
	 */
	public StatusBar(App<?, ?> context, GeneralUIField object)
	{
		super(context, object);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{
		// HP
	}

}
