/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db.sql

import java.sql.ResultSet

import spock.lang.Shared
import spock.lang.Specification

import com.dsc.db.Table
import com.dsc.db.sql.db.HsqlDB
import com.dsc.db.sql.server.HsqlServer

public class SQLTableImplTest extends Specification
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
		Object[][] arr=[[id,columnValue]]
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

	def createTable(String table,String column){
		db.exec(String.format("DROP TABLE IF EXISTS %s;CREATE TABLE %s(\
			id INT IDENTITY, \
			%s INT,\
			)",table,table,column))
	}

	def insert(String tableName,column,Object[][] values){
		insert(table,tableName,column,values)
	}

	def insert(Table table,String tableName,column,Object[][] values){
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,column),values)
	}

	def "deleteCascadely"(){

		//var
		String firstChildTableName="first_child_table"
		Table firstChildTable=db.table(firstChildTableName)
		String secondChildTableName="second_child_table"
		Table secondChildTable=db.table(secondChildTableName)
		String flagColumn="flag_column"
		int flagColumnValue=1
		int noiseRowValue=2
		String foreignKey="foreign_key"
		int firstNoiseForeignKey = 3
		int secondNoiseForeignKey = 4
		int thirdNoiseForeignKey = 5

		given:"parent table existed"
		createTable(tableName,flagColumn)
		and:"first child table existed"
		createTable(firstChildTableName,foreignKey)
		and:"second child table existed"
		createTable(secondChildTableName,foreignKey)
		and:"parent table set up"
		Object[][] arr=[[1,flagColumnValue]]//should be deleted
		insert(tableName,flagColumn,arr)
		arr=[[2,flagColumnValue]]//should be deleted
		insert(tableName,flagColumn,arr)
		arr=[[firstNoiseForeignKey,noiseRowValue]]//shouldn't be deleted
		insert(tableName,flagColumn,arr)
		and:"first child table set up"
		arr=[[1,1]]//should be deleted
		insert(firstChildTableName,foreignKey,arr)
		arr=[[2,2]]//should be deleted
		insert(firstChildTableName,foreignKey,arr)
		arr=[[firstNoiseForeignKey,firstNoiseForeignKey]]//shouldn't be deleted due to parent record!=flagValue
		insert(firstChildTableName,foreignKey,arr)
		and:"second child table set up"
		arr=[[1,1]]//should be deleted
		insert(secondChildTableName,foreignKey,arr)
		arr=[[2,secondNoiseForeignKey]]//should be deleted due to parent record!=flagValue
		insert(secondChildTableName,foreignKey,arr)
		arr=[[firstNoiseForeignKey,thirdNoiseForeignKey]]//shouldn't be deleted due to parent record!=flagValue
		insert(secondChildTableName,foreignKey,arr)

		when:"delete cascadely"
		table.deleteCascadely(flagColumn,flagColumnValue,ChildTable.from(firstChildTableName,foreignKey).and(secondChildTableName,foreignKey).all())
		and:"select left rows from parent table"
		ResultSet parentResult=table.selectBy("SELECT * FROM "+tableName)

		then:"noise row left"
		parentResult.next()==true
		parentResult.getObject(2).toString().equals(noiseRowValue.toString())
		and:"only one noise row left"
		parentResult.next()==false


		when:"select left rows from first child table"
		ResultSet firstChildResult=table.selectBy("SELECT * FROM "+firstChildTableName)

		then:"noise row left"
		firstChildResult.next()==true
		firstChildResult.getObject(2).toString().equals(firstNoiseForeignKey.toString())
		and:"only noise row left"
		firstChildResult.next()==false


		when:"select left rows from second child table"
		ResultSet secondChildResut=table.selectBy("SELECT * FROM "+secondChildTableName)

		then:"first noise row left"
		secondChildResut.next()==true
		secondChildResut.getObject(2).toString().equals(secondNoiseForeignKey.toString())
		and:"second noise row left"
		secondChildResut.next()==true
		secondChildResut.getObject(2).toString().equals(thirdNoiseForeignKey.toString())
		and:"only two noise rows left"
		parentResult.next()==false
	}

	//NP make it more concise
	def "select by specific column"(String columnType,String columnName,Object value,Object noiseRowValue){

		given:"table existed"
		db.exec(String.format("DROP TABLE IF EXISTS %s;CREATE TABLE %s(\
							id INT IDENTITY, \
							%s %s,\
							)",tableName,tableName,columnName,columnType))
		and:"first targeted row inserted"
		Object[][] arr=[[1,value]]
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"second targeted row inserted"
		arr=[[2,value]]
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"noise row inserted"
		arr=[[3,noiseRowValue]]
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
		Object[][] arr=[[1,value]]
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"second targeted row inserted"
		arr=[[2,value]]
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"noise row inserted"
		arr=[[3,noiseRowValue]]
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
		Object[][] arr=[[1,firstRowValue]]
		table.insert(String.format("insert into %s (id,%s) values (?,?)",tableName,columnName),arr)
		and:"another row inserted"
		arr=[[3,secondRowValue]]
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
		Object[][] arr=[[id,columnValue]]
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