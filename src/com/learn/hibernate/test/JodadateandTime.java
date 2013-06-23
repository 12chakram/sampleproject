package com.learn.hibernate.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;

import com.eng.gp.project.util.date.TimeZones;

public class JodadateandTime {

	public static void main(String[] args) throws ParseException {
		
	/*	DateTime startDate = new DateTime(2013, 05, 05, 23, 01, 44);
		Date sDate = startDate.toDate();
		System.out.println("------------");
		*/
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		
		TimeZone tz = TimeZone.getTimeZone("utc");
		String sdate = "05/05/2013";
		sdate = sdate.replace("/", "-");
		String edate = "05/31/2013";
		edate=edate.replace("/", "-");
		
		Date startDate = getDateInTimeZone(sdf.parse(sdate), tz);
		Date endDate = getDateInTimeZone(sdf.parse(edate), tz);
		
		
	}
	
	
	private static Date getDateInTimeZone(Date currentDate, TimeZone tz) {

		Calendar tzCal = new GregorianCalendar(tz);
		tzCal.setTimeInMillis(currentDate.getTime());

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, tzCal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, tzCal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, tzCal.get(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
}
