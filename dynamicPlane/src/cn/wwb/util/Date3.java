package cn.wwb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Date3 {
	public static String addDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dateTime = null;
		try {
			dateTime = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateTime);
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
		dateTime = calendar.getTime();
		String time = sdf.format(dateTime);
		return time;
	}	
	
//	public static void main(String[] args) {
//		addDate("20170601000000");
//	}
}
