/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

/**
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public interface API
{

	public API authorize(String token);

	public API authorize(String user, String password);

	public API DELETE(String data);

	public API domain(String domain);

	public API GET(String data);

	public API port(int port);

	public API POST(String data);

	// public API properties(String file);

	// public API restfull(String url);

	public String run() throws Exception;
}
