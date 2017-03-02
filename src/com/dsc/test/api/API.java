/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsc.test.api.base.ColumnCfg;

/**
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public interface API
{
	API test = new APITestImpl();

	public API authorize(String token);

	public API authorize(String user, String password);

	public API delete(String data);

	public API domain(String domain);

	public API excel(String file);

	public API excel(String file, ColumnCfg column);

	public API excel(String file, String sheet, ColumnCfg column);

	public API got(String data);

	public API name(String name);

	public API port(int port);

	public API post(String data);

	public API put(String data);

	public String resultAsCSV();

	public JSONObject returnJson();

	public JSONArray returnJsonArray();

	public String returnString();
}
