package com.dsc.selenium.util

import static com.dsc.selenium.util.SysUtil.*

import java.text.SimpleDateFormat

import spock.lang.Specification

public class SysUtilTest extends Specification {
	def "set system time under linux from long"(){
		//variables
		Date monthAgo=new Date(THIRTY_DAYS_AGO)

		when:"set system time to a far earlier time then get time"
		Date changedSysTime=SysUtil.setSystemTimeTo(monthAgo)

		then:"a triked time(far earlier then now) got returned"
		changedSysTime.getTime()-monthAgo.getTime()<=1000
	}

	def "set system time under linux from time string"(){
		//variables
		String TIME_STRING = "2016-06-08 23:11:01"

		when:"set system time to a far earlier time then get time"
		Date changedSysTime=SysUtil.setSystemTimeTo(TIME_STRING)

		then:"a tweaked time(far earlier then now) got returned"
		new SimpleDateFormat(SysUtil.DATE_TIME_FORMAT).format(changedSysTime)==TIME_STRING
	}

	//	def "set system time under windows"(){
	//		//variables
	//		Date monthAgo=new Date(THIRTY_DAYS_AGO)
	//
	//		when:"set system time to a far earlier time then get time"
	//		Date result=SysUtil.setSystemTimeTo(monthAgo)
	//
	//		then:"a triked time(far earlier then now) got returned"
	//		result.getTime()-THIRTY_DAYS_AGO<MILLISECONDS_IN_A_MINUTE
	//	}

	def cleanup(){
		SysUtil.enableTimeSync()
	}
}