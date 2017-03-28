/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pages;

import com.dsc.test.app.App;
import com.dsc.test.app.ui.base.AppComposite;

/**
 * @Author alex
 * @CreateTime 28.03.2017 13:07:34
 * @Version 1.0
 * @Since 1.0
 */
public class ContentPolicyView extends AppComposite
{
	/**
	 * @param context
	 */
	public ContentPolicyView(App<?, ?> app)
	{
		super(app, app.findElemByXpath("//*"));
		ensureAvailable();
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.base.Composite#ensureChildrenAvailable()
	 */
	@Override
	protected void ensureChildrenAvailable()
	{

	}
}
