/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db.sql.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class HsqlServerTest extends Thread {

	public static void main(String[] args) {
		new HsqlServerTest().start();
	}

	HsqlServer dbm = new HsqlServer();

	@Override
	public void run() {
		dbm.start();

		// some usefull server work here
		Connection conn = dbm.connection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS answers (num INT IDENTITY, answer VARCHAR(250))");
			stmt.executeQuery("INSERT INTO answers (answer) values ('this is a new answer')");
			ResultSet rs = stmt.executeQuery("SELECT num, answer FROM answers");
			while (rs.next()) {
				System.out.println("Answer number: " + rs.getString("num")
				+ "; answer text: " + rs.getString("answer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		dbm.stop();
	}
}

