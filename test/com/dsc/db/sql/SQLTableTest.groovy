/**
 00 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db.sql

import java.sql.ResultSet

import spock.lang.Shared
import spock.lang.Specification

import com.dsc.db.Table
import com.dsc.db.sql.db.HsqlDB
import com.dsc.db.sql.server.HsqlServer

public class SQLTableTest extends Specification
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

	//NP make it more concise
	def "select by specific column"(String columnType,String columnName,Object value,Object noiseRowValue){

		given:"table existed"
		db.exec(String.format("DROP TABLE IF EXISTS %s;CREATE TABLE %s(\
							id INT IDENTITY, \
							%s %s,\
							)",tableName,tableName,columnName,columnType))
		and:"first targeted row inserted"
		Object[][] arr=new Object[1][2]
		arr[0][0]=1
		arr[0][1]=value
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"second targeted row inserted"
		arr[0][0]=2
		arr[0][1]=value
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"noise row inserted"
		arr[0][0]=3
		arr[0][1]=noiseRowValue
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)

		when:"select by specific column"
		ResultSet res=table.selectBy(columnName,value)

		then:"first expected row got selected out"
		res.next()==true
		res.getObject(2).toString().equals(value.toString())//NP will toString() give any potential problem
		and:"second expected row got selected out"
		res.next()==true
		res.getObject(2).toString().equals(value.toString())//NP will toString() give any potential problem
		and:"only expected rows got selected out"
		res.next()==false

		where:
		columnType		|columnName			|value					|noiseRowValue
		"INT"			|"column_1"			|1						|2
		"INT"			|"column_long"		|1L						|2L
		"VARCHAR(250)"	|"column_string"	|"column_string_value"	|"noise row value"
	}

	def "delete by specific column"(String columnType,String columnName,Object value,Object noiseRowValue){

		given:"table existed"
		db.exec(String.format("DROP TABLE IF EXISTS %s;CREATE TABLE %s(\
					id INT IDENTITY, \
					%s %s,\
					)",tableName,tableName,columnName,columnType))
		and:"first targeted row inserted"
		Object[][] arr=new Object[1][2]
		arr[0][0]=1
		arr[0][1]=value
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"second targeted row inserted"
		arr[0][0]=2
		arr[0][1]=value
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"noise row inserted"
		arr[0][0]=3
		arr[0][1]=noiseRowValue
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)

		when:"delete by specific column"
		int deletedRowCount=table.deleteBy(columnName,value)

		then:"deletion succeed"
		deletedRowCount==2

		when:"select left value"
		ResultSet res=table.selectBy(columnName,noiseRowValue)

		then:"noise row still existed"
		res.next()==true
		res.getObject(2).toString().equals(noiseRowValue.toString())//NP will toString() give any potential problem
		and:"only noise row left"
		res.next()==false

		where:
		columnType		|columnName			|value					|noiseRowValue
		"INT"			|"column_1"			|1						|2
		"INT"			|"column_long"		|1L						|2L
		"VARCHAR(250)"	|"column_string"	|"column_string_value"	|"noise row value"
	}

	def "existed by specific column"(String columnType,String columnName,Object firstRowValue,Object secondRowValue){

		given:"table existed"
		db.exec(String.format("DROP TABLE IF EXISTS %s;CREATE TABLE %s(\
					id INT IDENTITY, \
					%s %s,\
					)",tableName,tableName,columnName,columnType))
		and:"first targeted row inserted"
		Object[][] arr=new Object[1][2]
		arr[0][0]=1
		arr[0][1]=firstRowValue
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"another row inserted"
		arr[0][0]=3
		arr[0][1]=secondRowValue
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)


		when:"check first row existing"
		boolean firstRowExisting=table.existedBy(columnName,firstRowValue)

		then:"noise row still existed"
		firstRowExisting==true

		when:"check second row existing"
		boolean secondRowExisting=table.existedBy(columnName,secondRowValue)

		then:"another row still existed also"
		secondRowExisting==true

		where:
		columnType		|columnName			|firstRowValue			|secondRowValue
		"INT"			|"column_1"			|1						|2
		"INT"			|"column_long"		|1L						|2L
		"VARCHAR(250)"	|"column_string"	|"first row value"		|"second row value"
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