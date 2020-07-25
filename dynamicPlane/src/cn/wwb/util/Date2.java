package cn.wwb.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Date2 {
	public static List<String> findDates(Date dBegin, Date dEnd) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		List<String> lDate = new ArrayList<String>();
		lDate.add(df.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(df.format(calBegin.getTime()));
		}
		return lDate;
	}

//	public static void main(String[] args) throws Exception {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date begin = sdf.parse("20170602");
//		Date end = sdf.parse("20170607");
//		List<String> dates = findDates(begin, end);
//		System.out.println(dates);
//		String dateString = sdf.format(new Date());
//		System.out.println(dateString);
//	}
}
