package com.dsc.db.sql

import java.sql.Timestamp

import spock.lang.Shared
import spock.lang.Specification

import com.dsc.db.Cell
import com.dsc.db.CellPair
import com.dsc.db.Column
import com.dsc.db.Columns
import com.dsc.db.DataBase
import com.dsc.db.RowPair
import com.dsc.db.sql.db.MySQL;
import com.dsc.db.sql.db.Postgresql;

public class SQLDBDataTest extends Specification
{
	@Shared DataBase mysql
	@Shared DataBase postgresql
	@Shared String table="for_value_testing"
	@Shared String insertRow="INSERT INTO "+table+" VALUES(?,?,?,?,?,?,?,?)"
	@Shared String createMySQLTable="CREATE TABLE "+table+"(id int,  int_value int, long_value bigint, char_value char(20), varchar_value varchar(250) NOT NULL, float_value float(20,8) NOT NULL, double_value double(20,8) NOT NULL, timestamp_value timestamp NOT NULL, primary key(id))"
	@Shared String createPostgresqlTable="CREATE TABLE "+table+"(id int,  int_value int, long_value bigint, char_value char(20), varchar_value varchar(250) NOT NULL, float_value real NOT NULL, double_value double precision NOT NULL, timestamp_value timestamp NOT NULL, primary key(id))"
	@Shared String dropTable="DROP TABLE IF EXISTS "+table+";"

	@Shared Object[][] data=[
		[1,1,1L,"char_1","varchar_1",1.1111f,1.11111111d,Timestamp.valueOf("2007-09-23 10:10:10.0")],
		[2,2,2L,"char_2","varchar_2",2.2222f,2.22222222d,Timestamp.valueOf("2007-09-23 10:10:20.0")],
		[3,3,3L,"char_3","varchar_3",3.3333f,3.33333333d,Timestamp.valueOf("2007-09-23 10:10:30.0")],
		[4,4,4L,"char_4","varchar_4",4.4444f,4.44444444d,Timestamp.valueOf("2007-09-23 10:10:40.0")],
		[5,5,5L,"char_5","varchar_5",5.5555f,5.55555555d,Timestamp.valueOf("2007-09-23 10:10:50.0")],
		[6,6,6L,"char_6","varchar_6",6.6666f,6.66666666d,Timestamp.valueOf("2007-09-23 10:10:60.0")],
		[7,7,7L,"char_7","varchar_7",7.7777f,7.77777777d,Timestamp.valueOf("2007-09-23 10:10:70.0")],
		[8,8,8L,"char_8","varchar_8",8.8888f,8.88888888d,Timestamp.valueOf("2007-09-23 10:10:80.0")],
	]

	def setupSpec(){
		mysql=MySQL.get().connect("test","root","112600827")
		postgresql=Postgresql.get().connect("test","postgres","112600827")

		mysql.exec(dropTable)
		mysql.exec(createMySQLTable)
		mysql.insert(insertRow,data)

		postgresql.exec(dropTable)
		postgresql.exec(createPostgresqlTable)
		postgresql.insert(insertRow,data)
	}

	def setup(){
		mysql.exec(dropTable)
		mysql.exec(createMySQLTable)
		mysql.insert(insertRow,data)

		postgresql.exec(dropTable)
		postgresql.exec(createPostgresqlTable)
		postgresql.insert(insertRow,data)
	}

	def cleanup(){
		mysql.exec(dropTable)
		postgresql.exec(dropTable)
	}

	def "all cell values in corresponded column are identical"(String column,_){

		when:"retrieve corresponded columns"
		Column mysql_column=mysql.table(table).primaryKey("id").column(column)
		Column postgres_column=postgresql.table(table).primaryKey("id").column(column)

		then:"no diff cell existe"
		mysql_column.diffsWith(postgres_column)==null

		where:
		column				|_
		"int_value"			|_
		//		"long_value"		|_
		//		"char_value"		|_
		//		"varchar_value"		|_
		//		"float_value"		|_
		//		"double_value"		|_
		//		"timestamp_value"	|_
	}

	def "single column comparison : key only existed one db"(String column,CellPair diff){
		//variable
		Object[][] mysqlDiffRow=[[0,0,0L,"char_1_diff","varchar_1_diff",0.1111f,0.11111111d,Timestamp.valueOf("2007-09-23 10:10:00.0")],]

		given:"diff cell existed"
		mysql.insert(insertRow,mysqlDiffRow)

		when:"retrieve corresponded columns then compare to get diffs"
		Column mysql_column=mysql.table(table).primaryKey("id").column(column)
		Column postgres_column=postgresql.table(table).primaryKey("id").column(column)
		List<CellPair> diffs = postgres_column.diffsWith(mysql_column)

		then:"diff count is as expected"
		diffs.size()==1
		//		and:"diff contect is as expected"
		//		diffs.get(0).equals(diff)

		where:
		column				|diff
		"int_value"			|new CellPair("id",new Cell("int_value",0,0), null)
		//		"long_value"		|new CellPair(new Cell("long_value",0,0L), null)
		//		"char_value"		|new CellPair(new Cell("char_value",0,"char_1_diff"), null)
		//		"varchar_value"		|new CellPair(new Cell("varchar_value",0,"varchar_1_diff"), null)
		//		"float_value"		|new CellPair(new Cell("float_value",0,0.1111f), null)
		//		"double_value"		|new CellPair(new Cell("double_value",0,0.11111111d), null)
		//		"timestamp_value"	|new CellPair(new Cell("timestamp_value",0,Timestamp.valueOf("2007-09-23 10:10:00.0")), null)
	}

	def "single column comparison : diff keys only existed one db"(String column,CellPair diff){
		//variable
		Object[][] mysqlDiffRow=[[0,0,0L,"char_1_diff","varchar_1_diff",0.1111f,0.11111111d,Timestamp.valueOf("2007-09-23 10:10:00.0")],]
		Object[][] postgresDiffRow=[[9,9,9L,"char_9_diff","varchar_9_diff",9.9999f,9.99999999d,Timestamp.valueOf("2007-09-23 10:10:90.0")]]

		given:"diff cell existed"
		mysql.insert(insertRow,mysqlDiffRow)
		postgresql.insert(insertRow,postgresDiffRow)

		when:"retrieve corresponded columns then compare to get diffs"
		Column mysql_column=mysql.table(table).primaryKey("id").column(column)
		Column postgres_column=postgresql.table(table).primaryKey("id").column(column)
		List<CellPair> diffs = postgres_column.diffsWith(mysql_column)

		then:"diff count is as expected"
		diffs.size()==2
		//		and:"diff contect is as expected"
		//		diffs.get(0).equals(diff)

		where:
		column				|diff
		"int_value"			|new CellPair("id",new Cell("int_value",0,0), null)
		//		"long_value"		|new CellPair(new Cell("long_value",0,0L), null)
		//		"char_value"		|new CellPair(new Cell("char_value",0,"char_1_diff"), null)
		//		"varchar_value"		|new CellPair(new Cell("varchar_value",0,"varchar_1_diff"), null)
		//		"float_value"		|new CellPair(new Cell("float_value",0,0.1111f), null)
		//		"double_value"		|new CellPair(new Cell("double_value",0,0.11111111d), null)
		//		"timestamp_value"	|new CellPair(new Cell("timestamp_value",0,Timestamp.valueOf("2007-09-23 10:10:00.0")), null)
	}

	def "single column comparison : same key and column,diff cell value"(String column,CellPair diff){
		//variable
		Object[][] mysqlDiffRow=[[0,0,0L,"char_1_diff","varchar_1_diff",0.1111f,0.11111111d,Timestamp.valueOf("2007-09-23 10:10:00.0")],]
		Object[][] postgresDiffRow=[[0,1,02L,"char_3_diff","varchar_4_diff",0.5555f,0.55555555d,Timestamp.valueOf("2007-09-23 10:10:59.0")],]

		given:"same keyed row has diff cell values"
		mysql.insert(insertRow,mysqlDiffRow)
		postgresql.insert(insertRow,postgresDiffRow)

		when:"retrieve corresponded columns then compare to get diffs"
		Column mysql_column=mysql.table(table).primaryKey("id").column(column)
		Column postgres_column=postgresql.table(table).primaryKey("id").column(column)
		List<CellPair> diffs = postgres_column.diffsWith(mysql_column)

		then:"diff count is as expected"
		diffs.size()==1
		//		and:"diff contect is as expected"
		//		diffs.get(0).equals(diff)

		where:
		column				|diff
		"int_value"			|new CellPair("id",new Cell("int_value",0,0), null)
		//		"long_value"		|new CellPair(new Cell("long_value",0,0L), null)
		//		"char_value"		|new CellPair(new Cell("char_value",0,"char_1_diff"), null)
		//		"varchar_value"		|new CellPair(new Cell("varchar_value",0,"varchar_1_diff"), null)
		//		"float_value"		|new CellPair(new Cell("float_value",0,0.1111f), null)
		//		"double_value"		|new CellPair(new Cell("double_value",0,0.11111111d), null)
		//		"timestamp_value"	|new CellPair(new Cell("timestamp_value",0,Timestamp.valueOf("2007-09-23 10:10:00.0")), null)
	}

	def "multiple columns comparison : same key and columns,diff column values"(){
		//variable
		String[] includeds=["int_value","long_value","char_value","varchar_value","float_value","double_value","timestamp_value"]
		Object[][] mysqlDiffRow=[[0,0,0L,"char_1_diff","varchar_1_diff",0.1111f,0.11111111d,Timestamp.valueOf("2007-09-23 10:10:00.0")],]
		Object[][] postgresDiffRow=[[0,1,02L,"char_3_diff","varchar_4_diff",0.5555f,0.55555555d,Timestamp.valueOf("2007-09-23 10:10:59.0")],]

		given:"same keyed row has diff cell values"
		mysql.insert(insertRow,mysqlDiffRow)
		postgresql.insert(insertRow,postgresDiffRow)

		when:"retrieve corresponded columns then compare to get diffs"
		Columns mysql_columns=mysql.table(table).primaryKey("id").columns(includeds)
		Columns postgres_columns=postgresql.table(table).primaryKey("id").columns(includeds)
		List<RowPair> diffs = postgres_columns.diffsWith(mysql_columns)

		then:"diff count is as expected"
		diffs.size()==1
	}

	def "multiple columns comparison : same key and diff column values but they are excluded from comparison"(){
		//variable
		String[] excludeds=["int_value","char_value","float_value","timestamp_value"]
		Object[][] mysqlDiffRow=[[0,0,0L,"char_1"     ,"varchar_1",0.1111f,0.11111111d,Timestamp.valueOf("2007-09-23 10:10:00.0")],]
		Object[][] ptgrsDiffRow=[[0,1,0L,"char_1_diff","varchar_1",0.1112f,0.11111111d,Timestamp.valueOf("2007-09-24 10:10:00.0")],]

		given:"same keyed row has diff cell values"
		mysql.insert(insertRow,mysqlDiffRow)
		postgresql.insert(insertRow,ptgrsDiffRow)

		when:"retrieve columns except excludeds then compare to get diffs"
		Columns mysql_columns=mysql.table(table).primaryKey("id").columnsExcept(excludeds)
		Columns postgres_columns=postgresql.table(table).primaryKey("id").columnsExcept(excludeds)
		List<RowPair> diffs = postgres_columns.diffsWith(mysql_columns)

		then:"no diffs due to corresponded columns has been excluded from comparison"
		diffs==null
	}

	def "multiple columns comparison : same key and diff column values but most of them excluded from comparison except two"(){
		//variable
		String[] excludeds=["char_value","float_value"]//"int_value"'s value is different but not excluded from comparison
		Object[][] mysqlDiffRow=[[0,0,0L,"char_1"     ,"varchar_1",0.1111f,0.11111111d,Timestamp.valueOf("2007-09-23 10:10:00.0")],]
		Object[][] ptgrsDiffRow=[[0,1,0L,"char_1_diff","varchar_1",0.1112f,0.11111111d,Timestamp.valueOf("2007-09-24 10:10:00.0")],]

		given:"same keyed row has diff cell values"
		mysql.insert(insertRow,mysqlDiffRow)
		postgresql.insert(insertRow,ptgrsDiffRow)

		when:"retrieve columns except excludeds then compare to get diffs"
		Columns mysql_columns=mysql.table(table).primaryKey("id").columnsExcept(excludeds)
		Columns postgres_columns=postgresql.table(table).primaryKey("id").columnsExcept(excludeds)
		List<RowPair> diffs = postgres_columns.diffsWith(mysql_columns)

		then:"diff got found due to some diff column not excluded"
		diffs.size()==1
	}
}