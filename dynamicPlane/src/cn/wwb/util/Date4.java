package cn.wwb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Date4 {
//	public static void main(String[] args) {
//		Date date = new Date();
//		List<String> list = new ArrayList<String>();
//		List<Date> ds = test("20170601000000", "20170601090000");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		for (Date d : ds) {
//			String string = sdf.format(d);
//			System.out.println(string);
//			list.add(string);
//		}
//		
//	}
	
	public static List<Date> test(String date1, String date2) {
		String time1 = date1;
		String time2 = date2;
		
		List<Date> result = new ArrayList<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date dateTime1 = sdf.parse(time1);
			Date dateTime2 = sdf.parse(time2);
			
			Date start = dayStartDate(dateTime1);//转换为天的起始date
			Date nextDayDate = nextDay(dateTime2);//下一天的date			
			
			while (start.compareTo(nextDayDate) < 0) {
				result.add(start);
				//日期加30分钟
				start = addThirtyMin(start, 30);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	private static Date addThirtyMin(Date start, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MINUTE, offset);
		return c.getTime();
	}

	private static Date nextDay(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 0);
		return c.getTime();
	}

	private static Date dayStartDate(Date date) {
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
		return c.getTime();	
	}
}
