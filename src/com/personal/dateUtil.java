package com.personal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//This class contains Static methods to Describe date
public class dateUtil {
	
	public static String mname[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
//method accepts date in String and return date Object
	public static Date stringToDate(String stringdate) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return df.parse(stringdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
//method accepts date Object and return date in string Format	
	public static String DateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
	
//method accepts date object and return year and month number in String format(used for monthly report) 
	public static String yearmonth(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy,MM");
		return df.format(date);
	}
//method accepts date object and return year in integer(used for yearly report)	
	public static Integer getyear(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return new Integer(df.format(date));
	}
//accept month number and returns month name from array	
	public static String monthNumToName(Integer mno) {
		return mname[mno-1];
	}

}
