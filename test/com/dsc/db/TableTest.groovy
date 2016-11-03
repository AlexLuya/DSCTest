/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db



import java.sql.ResultSet

import spock.lang.Shared
import spock.lang.Specification

import com.dsc.db.sql.HsqlDB

public class TableTest extends Specification
{
	@Shared HsqlDB db
	@Shared Table table
	@Shared	String tableName="for_crud_testing"

	def setupSpec(){
		db=HsqlDB.get()

		db.start()

		db.connect(HsqlServer.DB)

		table=db.table(tableName)
	}

	def cleanupSpec(){
		db.stop()
	}

	def "select by id"(Object id,_){
		//variables
		String columnValue="the content of value 1"

		given:"table existed"
		db.exec("DROP TABLE IF EXISTS "+tableName+";CREATE TABLE "+tableName+"(\
							id INT IDENTITY, \
							value_1 VARCHAR(250),\
		)")
		and:"row existed"
		//NP quite ugly,concise blow three lines
		Object[][] arr=new Object[1][2]
		arr[0][0]=id
		arr[0][1]=columnValue
		table.insert(String.format("insert into %s (id,value_1) values (?,?)",tableName),arr)

		when:"select by id"
		ResultSet row=table.selectById(id)

		then:"expected row got selected out"
		row.next()==true
		row.getString(2)==columnValue

		where:
		id	|_
		1	|_
		1L	|_
		"1"	|_
	}

	def "delete by id"(){
		//variables
		Object id=1
		String columnValue="the content of value 1"

		given:"table existed"
		db.exec("DROP TABLE IF EXISTS "+tableName+";CREATE TABLE "+tableName+"(\
							id INT IDENTITY, \
							value_1 VARCHAR(250),\
							)")
		and:"row existed"
		//NP quite ugly,concise blow three lines
		Object[][] arr=new Object[1][2]
		arr[0][0]=id
		arr[0][1]=columnValue
		table.insert(String.format("insert into %s (id,value_1) values (?,?)",tableName),arr)

		when:"select by id"
		ResultSet row=table.selectById(id)

		then:"expected row got selected out"
		row.next()==true
		row.getString(2)==columnValue

		when:"delete by id"
		table.deleteById(id)
		and:"select that row again"
		row=table.selectById(id)

		then:"result set is empty"
		row.next()==false
	}
}