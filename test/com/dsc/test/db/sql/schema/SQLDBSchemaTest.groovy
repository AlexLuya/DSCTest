package com.dsc.test.db.sql.schema

import spock.lang.Shared
import spock.lang.Specification

import com.dsc.test.db.Columns
import com.dsc.test.db.sql.db.HsqlDB;
import com.dsc.test.db.sql.server.HsqlServer;

public class SQLDBSchemaTest extends Specification
{
	@Shared HsqlDB db
	def setupSpec(){
		db=HsqlDB.get()

		db.start()

		db.connect(HsqlServer.DB)
	}

	def cleanupSpec(){
		db.stop()
	}

	def "char typed columns hasn't NOT NULL constrain"(){
		//variables
		String table="char_typed"

		given:"some char typed columns has not NOT NULL constrain"
		db.exec("DROP TABLE IF EXISTS "+table+";CREATE TABLE "+table+"(\
							id INT IDENTITY, \
							nullable_varchar_column VARCHAR(250),\
							varchar_with_not_null VARCHAR(250) NOT NULL,\
							nullable_char_column CHAR(250),\
							char_with_not_null CHAR(250) NOT NULL,\
		)")

		when:"check whether all char typed column has NOT NULL constrain"
		List<String> nullableCharColumns=db.table(table).charTypedColumns().nullables()


		then:"some char types columns are nullable"
		nullableCharColumns!=null
	}

	def "foreign key referencing not enforced"(){
		//variables
		String table="foreign_key_referencing_not_enforced"
		String has_foreign_key="has_foreign_key"
		String has_not_foreign_key="has_not_foreign_key"

		given:"referee table existed"
		db.exec("DROP TABLE IF EXISTS "+table+";DROP TABLE IF EXISTS referee;CREATE TABLE referee(\
							id INT IDENTITY, \
							not_refered_column INT NOT NULL,\
						)")
		and:"only one column has refercing constrained"
		db.exec("DROP TABLE IF EXISTS "+table+";CREATE TABLE "+table+"(\
							id INT IDENTITY, \
							"+has_foreign_key+" INT NOT NULL,\
							"+has_not_foreign_key+" INT NOT NULL,\
							CONSTRAINT FK FOREIGN KEY ("+has_foreign_key+") REFERENCES referee(id),\
		)")

		when:"retrieve all columns"
		Columns columns=db.table(table).columns()


		then:"some column has foreign key refering constrain"
		columns.itemByName(has_foreign_key).referencedColumn().name()=="id"
		and:"some hasn't"
		columns.itemByName(has_not_foreign_key).referencedColumn()==null
	}

	def "foreign key NULLABLE unexpectedly"(){
		//variables
		String table="foreign_key_referencing_not_enforced"

		given:"referee tables existed"
		db.exec("DROP TABLE IF EXISTS "+table+";DROP TABLE IF EXISTS referee_1;CREATE TABLE referee_1(\
							id INT IDENTITY, \
						)")
		db.exec("DROP TABLE IF EXISTS "+table+";DROP TABLE IF EXISTS referee_2;CREATE TABLE referee_2(\
							id INT IDENTITY, \
						)")
		db.exec("DROP TABLE IF EXISTS "+table+";DROP TABLE IF EXISTS referee_3;CREATE TABLE referee_3(\
							id INT IDENTITY, \
						)")
		and:"some refercing columns are nullable"
		db.exec("DROP TABLE IF EXISTS "+table+";CREATE TABLE "+table+"(\
							id INT IDENTITY, \
							referee_1_id INT,\
							referee_2_id INT NOT NULL,\
							referee_3_id INT,\
							CONSTRAINT FK_Referee_1 FOREIGN KEY (referee_1_id) REFERENCES referee_1(id),\
							CONSTRAINT FK_Referee_2 FOREIGN KEY (referee_2_id) REFERENCES referee_2(id),\
							CONSTRAINT FK_Referee_3 FOREIGN KEY (referee_3_id) REFERENCES referee_3(id),\
		)")

		when:"retrieve all columns"
		Columns hasReferenceColumns=db.table(table).hasReferenceColumns()

		then:"some foreign keys are nullable"
		hasReferenceColumns.nullables()==["referee_1_id","referee_3_id"]
	}

	def "some must have auditing columns missing"(){
		//variables
		String table="some_auditing_columns_missing"

		given:"some must have auditing columns missing"
		db.exec("DROP TABLE IF EXISTS "+table+";CREATE TABLE "+table+"(\
							id INT IDENTITY, \
							creator_id INT NOT NULL,\
							create_time datetime NOT NULL,\
							id_in_source INT NOT NULL,\
							creator_id_in_source INT NOT NULL,\
		)")

		when:"retrieve all columns"
		Columns columns=db.table(table).columns()

		then:"some must have auditing columns existed"
		columns.ensureExisted("id","creator_id","create_time","id_in_source","creator_id_in_source","create_time_in_source")==["create_time_in_source"]
	}

	def "column data type is wrong"(){
		//variables
		String table="data_type_is_wrong"

		given:"some char typed columns's date type aren't expected"
		db.exec("DROP TABLE IF EXISTS "+table+";CREATE TABLE "+table+"(\
							id INT IDENTITY, \
							creator_id INT NOT NULL,\
							create_time datetime NOT NULL,\
							id_in_source INT NOT NULL,\
							creator_id_in_source INT NOT NULL,\
							create_time_in_source TIMESTAMP NOT NULL,\
		)")

		when:"retrieve all columns"
		Columns columns=db.table(table).columns()


		then:"some columns's data type is wrong"
		columns.itemByName("create_time_in_source").dataType()!="datetime"
	}

	def "column data size is wrong"(){
		given:""

		when:""

		then:""
	}

	def "char typed column missing default value"(){
		//variables
		String table="default_value_missing"

		given:"some char typed columns misses default value"
		db.exec("DROP TABLE IF EXISTS "+table+";CREATE TABLE "+table+"(\
							id INT IDENTITY, \
							with_default_value VARCHAR(10) DEFAULT 'UNKNOWN' NOT NULL,\
							without_default_value VARCHAR(10) NOT NULL,\
		)")

		when:"retrieve all columns"
		Columns columns=db.table(table).columns()


		then:"some char typed columns has default value"
		columns.itemByName("with_default_value").defaultValue()=="'UNKNOWN'"//NP why '' needed?
		then:"some char typed columns has NOT default value"
		columns.itemByName("without_default_value").defaultValue()==null
	}

	def "comment missing"(){

	}
}