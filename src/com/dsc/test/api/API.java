/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import java.io.IOException;

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

	API basicAuth(String user, String password);

	API cookie(String name, Object value);

	API delete(String data);

	API domain(String domain);

	API excel(String file);

	API excel(String file, ColumnCfg column);

	API excel(String file, int ignoredRows, ColumnCfg column);

	API excel(String file, int ignoredRows, String sheet, ColumnCfg column);

	API excel(String file, String sheet, ColumnCfg column);

	API formParam(String name, Object value);

	API get(String data);

	API header(String name, Object value);

	API name(String name);

	API patch(String data);

	API port(int port);

	API post(String data);

	API put(String data);

	String resultAsExcel() throws IOException;

	JSONObject returnJson();

	JSONArray returnJsonArray();

	String returnString();

	String upload(String file);
}
